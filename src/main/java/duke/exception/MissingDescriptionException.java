package duke.exception;

/**
 * <code>MissingDescriptionException</code> is thrown when the user did not input a
 * description for task creation commands.
 */
public class MissingDescriptionException extends DukeException {

    private final String message = "☹ OOPS!!! You did not provide a description for the task!";

    /**
     * Constructor of <code>MissingDescriptionException</code>.
     */
    public MissingDescriptionException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return message;
    }

}
