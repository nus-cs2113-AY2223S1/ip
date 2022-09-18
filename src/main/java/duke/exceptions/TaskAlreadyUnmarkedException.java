package duke.exceptions;

public class TaskAlreadyUnmarkedException extends DukeException {
    /**
     * Prints an error message to inform the user that they are attempting to unmark a task that is already unmarked.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already marked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
