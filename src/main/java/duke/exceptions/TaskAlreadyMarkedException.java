package duke.exceptions;

public class TaskAlreadyMarkedException extends DukeException {
    /**
     * Prints an error message to inform the user that they are attempting to mark a task that is already done.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already marked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
