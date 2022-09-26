package duke.exception;

/**
 * <code>NotIntegerException</code> is thrown when the user inputs characters other than
 * integers for tasks that require integers to indicate the task of interest.
 */
public class NotIntegerException extends DukeException {
    private final String message;

    /**
     * Constructor of <code>NotIntegerException</code>.
     */
    public NotIntegerException(String message) {
        super();
        this.message = message;
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + message + " is not an integer. Please try again with an integer.";
    }
}
