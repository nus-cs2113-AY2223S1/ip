package duke.exceptions;

public class MissingTaskNumberException extends DukeException {
    /**
     * Prints an error message to inform the user that they did not provide a task number.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! You did not specify the task number :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
