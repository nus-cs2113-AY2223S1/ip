package duke.exception;

import duke.task.TaskList;
import duke.manager.Parser;

public class ExceptionChecker {


    public static void checkFlagExistence(String input, String keyword) throws MissingFlagException {

        int flagPosition = Parser.getFlagPosition(input, keyword);
        if (flagPosition < 0) {
            throw new MissingFlagException();
        }
    }


    public static void checkArrayAccess(TaskList taskList, int taskPosition) throws NullPointerException,
            ArrayIndexOutOfBoundsException, NoTasksException {

        if (taskList.getSize() == 0) {
            throw new NoTasksException();
        }
        try {
            taskList.get(taskPosition);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static int checkIntegerType(String description) throws NumberFormatException {
        int taskPosition;
        try {
            taskPosition = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return taskPosition;
    } // only for mark, unmark and delete

    public static void checkDescription(String description) throws MissingDescriptionException {
        if (description.equals("")) {
            throw new MissingDescriptionException();
        }
    }

    public static void checkDescriptionAndTime(String description, String time) throws
            MissingDescriptionAndTimeException, MissingTimeException, MissingDescriptionException {
        if (time.equals("")) {
            if (description.equals("")) {
                throw new MissingDescriptionAndTimeException();
            } else {
                throw new MissingTimeException();
            }
        } else if (description.equals("")) {
            throw new MissingDescriptionException();
        }
    }

    public static void checkNumberOfArguments(String input, String keyword) throws TooManyArgumentsException {

        // only for list, bye, mark, unmark, delete
        String[] splitInput = input.split(" ");
        switch (keyword) {
        case "bye":
        case "list":
            if (splitInput.length > 1) {
                throw new TooManyArgumentsException();
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (splitInput.length > 2) {
                throw new TooManyArgumentsException();
            }
            break;
        }
    }

    public static void isLegal(TaskList taskList, String input, String description, String time, String keyword) {
        int taskPosition;
        try {
            switch (keyword) {
            case "bye":
            case "list":
                checkNumberOfArguments(input, keyword);
                break;
            case "mark":
            case "unmark":
            case "delete":
                checkNumberOfArguments(input, keyword);
                taskPosition = checkIntegerType(description);
                checkArrayAccess(taskList, taskPosition);
                break;
            case "todo":
                checkDescription(description);
                break;
            case "event":
            case "deadline":
                checkFlagExistence(input, keyword);
                checkDescriptionAndTime(description, time);
            }
        } catch (TooManyArgumentsException e) {
            System.out.println("☹ OOPS!!! You provided too many arguments!");
        } catch (NoTasksException e) {
            System.out.println("☹ OOPS!!! You don't have any tasks yet. Why not try creating some?");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! You did not provide me with an integer!");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! You don't have that many tasks!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! You don't have that many tasks!");
        } catch (MissingDescriptionException e) {
            System.out.println("☹ OOPS!!! You did not provide a description for the task!");
        } catch (MissingTimeException e) {
            System.out.println("☹ OOPS!!! You did not provide a time for the task!");
        } catch (MissingDescriptionAndTimeException e) {
            System.out.println("☹ OOPS!!! You did not provide both a description and time for the task!");
        } catch (MissingFlagException e) {
            System.out.println("☹ OOPS!!! You did not provide a by or at flag!");
        }
    }
}

