package duke.command;

import duke.exception.MissingDescriptionException;
import duke.exception.MissingTimeException;
import duke.task.Task;

public class ExceptionHandler {

    public static int handleNotInteger(String description) throws NumberFormatException {
        int whichTask;
        try {
            whichTask = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return whichTask;
    } // only for mark, unmark and delete

    public static void handleOutOfBounds(Task[] tasks, int taskPosition, String keyword) throws NullPointerException,
            ArrayIndexOutOfBoundsException {

        try {
            if (keyword.equals("mark")) {
                tasks[taskPosition].markAsDone();
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                        + tasks[taskPosition]);
            } else if (keyword.equals("unmark")) {
                tasks[taskPosition].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                        + tasks[taskPosition]);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    } // only for mark, unmark and delete

    public static void handleTooManyArguments(String[] s, String keyword, Task[] tasks) {
        switch (keyword) {
        case "list":
        case "bye":
            if (s.length >= 2) {
                System.out.println("☹ OOPS!!! You gave me too many arguments!");
            }   else {
                Allocator.processInput(s, keyword, tasks);
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (s.length >= 3) {
                System.out.println("☹ OOPS!!! You gave me too many arguments!");
            }   else {
                Allocator.processInput(s, keyword, tasks);
            }
            break;
        default:
            Allocator.processInput(s, keyword, tasks);
            break;
        }
    }

    public static void checkDescription(String description, String keyword) throws
            MissingDescriptionException {
        switch (keyword) {
        case "todo":
        case "deadline":
        case "event":
            if (description.equals("")) {
                throw new MissingDescriptionException();
            }
            break;
        default:
            break;
        }
    }

    public static void checkTime(String time, String keyword) throws MissingTimeException {
        switch (keyword) {
        case "deadline":
        case "event":
            if (time.equals("")) {
                throw new MissingTimeException();
            }
            break;
        default:
            break;
        }
    }
}

