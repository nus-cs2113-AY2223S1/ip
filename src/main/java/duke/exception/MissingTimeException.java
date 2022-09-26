package duke.exception;

/**
 * <code>MissingTimeException</code> is thrown when the user did not input a
 * time for deadline or event task creation.
 */
public class MissingTimeException extends DukeException {

    /**
     * Constructor of <code>MissingTimeException</code>.
     */
    public MissingTimeException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! You did not provide the time for the command.";
    }
}
