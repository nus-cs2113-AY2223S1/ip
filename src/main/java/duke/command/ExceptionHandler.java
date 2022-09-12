package duke.command;

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
}

