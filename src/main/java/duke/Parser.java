package duke;

import duke.commands.*;
import duke.exceptions.EmptyCommandException;
import duke.exceptions.EmptyTasklineException;
import duke.exceptions.IncorrectFormatInputException;

/**
 * The Parser class aims to make sense of the inputs so that
 * the input can be understood to perform the later actions.
 */
public class Parser {

    /**
     * Separate the input line into 2 parts:
     * 1st word as the commandWord, 2nd part for the rest of the content as the taskline.
     * Return null if the input is not valid (instances of exceptions catched)
     *
     * @param input the line of String that the user typed in
     * @return Command with respect to its command word
     */
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

    /**
     * To differentiate the different command words to its respective action to be performed.
     *
     * @param commandWord the action word in input
     * @param taskLine the content after command word
     * @return Command with respect to its command word
     * @throws EmptyCommandException if the first word is not a command word
     * @throws EmptyTasklineException if there is no real content in taskline
     * @throws IncorrectFormatInputException if the taskline does not only contain numbers
     */
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

    /**
     * Categorised together since the input required for marking or deleting is similar.
     * For differentiating the words to create the different child objects for Command.
     * Return Command with respect to its command word.
     *
     * @param taskline the content after command word
     * @param commandWord the action word in input
     * @return Command with respect to its command word
     * @throws EmptyTasklineException if there is no real content in taskline
     * @throws IncorrectFormatInputException if the taskline does not only contain numbers
     */
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

    /**
     * Check whether the input for marking or deleting action is in the correct format.
     * Meaning, there is only the number index following after the command word.
     *
     * @param taskline the content after command word
     * @throws IncorrectFormatInputException if the taskline does not only contain numbers
     */
    private static void findIncorrectFormatInputExceptionIndex(String taskline) throws IncorrectFormatInputException {
        int l = taskline.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(taskline.charAt(i))) {
                throw new IncorrectFormatInputException();
            }
        }
    }

    /**
     * Check whether the input is valid.
     * Meaning, whether there is content followed after the command word.
     *
     * @param taskline the content after command word
     * @throws EmptyTasklineException if there is no real content in taskline
     */
    private static void findEmptyTasklineException(String taskline) throws EmptyTasklineException {
        if (taskline.equals(" ")) {
            throw new EmptyTasklineException();
        }
    }

    /**
     * Check the intention of the marking action: 'mark' or 'unmark'.
     * Return true if 'mark', false if 'unmark'.
     *
     * @param commandWord the action word in input
     * @return boolean value
     */
    public static boolean completion(String commandWord) {
        return commandWord.equals("mark");
    }

    /**
     * Categorised together since the its all about the creation and addition of Task.
     * For differentiating the words to create the different child objects for AddCommand.
     * Further separate the taskline into 2 parts in instance of 'deadline' and 'event':
     * 1st part is the task description, 2nd part is the time information.
     * Return Command with respect to its command word.
     *
     * @param taskline the content after the command word
     * @param commandWord the action word in input
     * @return Command with respect to its command word
     * @throws EmptyTasklineException if there is no real content after command word
     * @throws IncorrectFormatInputException if input does not follow the required format
     */
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
