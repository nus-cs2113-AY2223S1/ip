package duke.exceptions;

public class AccessTaskOutOfBoundsException extends DukeException {
    /**
     * Prints an error message to inform the user that they tried to index a task that is out of bounds.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you have specified is out of bounds :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
