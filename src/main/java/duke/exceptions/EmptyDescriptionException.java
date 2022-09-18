package duke.exceptions;

public class EmptyDescriptionException extends DukeException {

    /**
     * Prints an error message to inform the user that they did not provide a task description.
     */
    @Override
    public void printErrorMessage() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}