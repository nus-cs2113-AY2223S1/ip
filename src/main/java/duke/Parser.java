package duke;

import duke.commands.*;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.EmptyTasklineException;
import duke.exceptions.IncorrectFormatInputException;

public class Parser {

    public static Command parserCommand(String input) {
        String[] words = input.split(" ");
        String commandWord = words[0];
        int length = commandWord.length();
        String taskLine = " ";
        if (input.length() > length) {
            taskLine = input.substring(length + 1);
        }

        Command c = null;
        try {
            c = specificCase(commandWord, taskLine);
        } catch(EmptyCommandException e) {
            System.out.println("\tSorry what is your intended action?");
        } catch(EmptyTasklineException e) {
            System.out.println("\tThere is no content assigned!");
        } catch(IncorrectFormatInputException e) {
            System.out.println("\tThe format of your input content is wrong!!");
        }

        return c;
    }

    private static Command specificCase(String commandWord, String taskLine) throws EmptyCommandException,
            EmptyTasklineException, IncorrectFormatInputException {
        switch (commandWord) {
        case "bye":
            return new ExitCommand(commandWord);
        case "list":
            return new ListCommand(commandWord);
        case "find":
            return new FindCommand(commandWord, taskLine);
        case "mark":
        case "unmark":
        case "delete":
            return differentiateAction(taskLine, commandWord);
        case "todo":
        case "deadline":
        case "event":
            return differentiateTask(taskLine, commandWord);
        default:
            throw new EmptyCommandException();
        }
    }

    public static Command differentiateAction(String taskline, String commandWord) throws EmptyTasklineException,
            IncorrectFormatInputException {
        findEmptyTasklineException(taskline);
        findIncorrectFormatInputExceptionIndex(taskline);

        int index = Integer.parseInt(taskline) - 1;
        if (commandWord.equals("delete")) {
            return new DeleteCommand(commandWord, index);
        } else {
            boolean isComplete = completion(commandWord);
            return new MarkCommand(commandWord, index, isComplete);
        }
    }

    private static void findIncorrectFormatInputExceptionIndex(String taskline) throws IncorrectFormatInputException {
        int l = taskline.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(taskline.charAt(i))) {
                throw new IncorrectFormatInputException();
            }
        }
    }

    private static void findEmptyTasklineException(String taskline) throws EmptyTasklineException {
        if (taskline.equals(" ")) {
            throw new EmptyTasklineException();
        }
    }

    public static boolean completion(String commandWord) {
        return commandWord.equals("mark");
    }

    public static Command differentiateTask(String taskline, String commandWord) throws EmptyTasklineException,
            IncorrectFormatInputException {
        findEmptyTasklineException(taskline);

        if (commandWord.equals("todo")) {
            return new AddTodoCommand(commandWord, taskline);
        } else if (commandWord.equals("deadline")) {
            findIncorrectFormatInputExceptionD(taskline);
            int taskLength = findTaskDLength(taskline);
            String by = taskline.substring(taskLength + 5);
            String taskInfo = taskline.substring(0, taskLength);
            return new AddDeadlineCommand(commandWord, taskInfo, by);
        } else {
            findIncorrectFormatInputExceptionE(taskline);
            int taskLength = findTaskELength(taskline);
            String at = taskline.substring(taskLength + 5);
            String taskInfo = taskline.substring(0, taskLength);
            return new AddEventCommand(commandWord, taskInfo, at);
        }
    }

    private static void findIncorrectFormatInputExceptionD(String taskline) throws IncorrectFormatInputException {
        if (!taskline.contains(" /by ")) {
            throw new IncorrectFormatInputException();
        }
    }

    private static void findIncorrectFormatInputExceptionE(String taskline) throws IncorrectFormatInputException {
        if (!taskline.contains(" /at ")) {
            throw new IncorrectFormatInputException();
        }
    }

    public static int findTaskDLength(String line) {
        return line.indexOf(" /by ");
    }

    public static int findTaskELength(String line) {
        return line.indexOf(" /at ");
    }
}
