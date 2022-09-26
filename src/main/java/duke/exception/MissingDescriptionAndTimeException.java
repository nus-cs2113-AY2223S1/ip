package duke.exception;

/**
 * <code>MissingDescriptionAndTimeException</code> is thrown when the user did not input a
 * description and time for task creation commands.
 */
public class MissingDescriptionAndTimeException extends DukeException {

    /**
     * Constructor of <code>MissingDescriptionAndTimeException</code>.
     */
    public MissingDescriptionAndTimeException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! You did not provide both a description and time for the task!";
    }
}
