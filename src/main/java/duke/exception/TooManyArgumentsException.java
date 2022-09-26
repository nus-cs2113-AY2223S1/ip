package duke.exception;

/**
 * <code>TooManyArgumentsException</code> is thrown when the user inputs too many arguments.
 */
public class TooManyArgumentsException extends DukeException {

    /**
     * Constructor of <code>TooManyArgumentsException</code>.
     */
    public TooManyArgumentsException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! You have provided too many arguments.";
    }
}
