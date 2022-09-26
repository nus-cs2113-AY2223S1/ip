package duke.exception;

import duke.task.TaskList;
import duke.manager.Parser;

public class ExceptionChecker {

    // TAKE NOTE: SOME EXCEPTIONS CAUSES REPEATED EXCEPTION HANDLING MESSAGE PRINTING - TO FIX

    public static final String EMPTY_STRING = "";

    private static boolean isEmptyDescription(String description) {
        return description.equals(EMPTY_STRING);
    }

    private static boolean isEmptyTime(String time) {
        return time.equals(EMPTY_STRING);
    }

    public static boolean doesFlagExists(int flagPosition) {
        if (flagPosition < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void checkFlagExistence(String input, String keyword) throws MissingFlagException {
        int flagPosition = Parser.getFlagPosition(input, keyword);
        if (!doesFlagExists(flagPosition)) {
            throw new MissingFlagException(keyword);
        }
    }

    public static void checkArrayAccess(int taskPosition) throws NoTasksException,
            OutOfBoundsException {
        if (TaskList.getSize() == 0) {
            throw new NoTasksException();
        } else if (taskPosition < 0 || taskPosition >= TaskList.getSize()) {
            throw new OutOfBoundsException();
        }
    }

    public static int checkIntegerType(String description) throws NotIntegerException {
        int taskPosition;
        try {
            taskPosition = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new NotIntegerException(description);
        }
        return taskPosition;
    } // only for mark, unmark and delete

    public static void checkDescription(String description) throws MissingDescriptionException {
        if (isEmptyDescription(description)) {
            throw new MissingDescriptionException();
        }
    }

    public static void checkDescriptionAndTime(String description, String time) throws
            MissingDescriptionAndTimeException, MissingTimeException, MissingDescriptionException {
        if (isEmptyTime(time)) {
            if (isEmptyDescription(description)) {
                throw new MissingDescriptionAndTimeException();
            } else {
                throw new MissingTimeException();
            }
        } else if (isEmptyDescription(description)) {
            throw new MissingDescriptionException();
        }
    }

    /*public static void checkParameters(String input, String keyword) {
       // to throw description, flag, time existence checking all into one function
    }*/

    public static void checkNumberOfArguments(String input, String keyword) throws TooManyArgumentsException,
            MissingIntegerException {
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
            } else if (splitInput.length == 1) {
                throw new MissingIntegerException(keyword);
            }
            break;
        }
    }

    public static boolean isExceptionFree(String input, String description, String time, String keyword) {
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
                checkArrayAccess(taskPosition);
                break;
            case "todo":
                checkDescription(description);
                break;
            case "event":
            case "deadline":
                checkFlagExistence(input, keyword);
                checkDescriptionAndTime(description, time);
                break;
            default:
                break;
            }
        } catch (TooManyArgumentsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NoTasksException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NotIntegerException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (OutOfBoundsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (MissingIntegerException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (MissingDescriptionException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (MissingTimeException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (MissingDescriptionAndTimeException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (MissingFlagException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}

