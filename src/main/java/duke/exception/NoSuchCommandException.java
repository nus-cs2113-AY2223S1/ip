package duke.exception;

/**
 * <code>NoSuchCommandException</code> is thrown when the user inputs a command not
 * recognised by Duke.
 */
public class NoSuchCommandException extends DukeException {

    /**
     * Constructor of <code>NoSuchCommandException</code>.
     */
    public NoSuchCommandException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "OOPS!!! You did not provide a valid command.";
    }
}
