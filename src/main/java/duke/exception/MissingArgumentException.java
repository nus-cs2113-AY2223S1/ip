package duke.exception;

/**
 * <code>MissingArgumentException</code> is thrown when the user did not provide
 * enough arguments for the execution of commands.
 */
public class MissingArgumentException extends DukeException {

    private final String keyword;

    /**
     * Constructor of <code>MissingArgumentException</code>.
     * Stores the type of command for error message printing.
     *
     * @param message the type of command
     */
    public MissingArgumentException(String message) {
        super();
        this.keyword = message;
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        String partOne = "☹ OOPS!!! Your ";
        String partTwo = " command is missing an argument!";
        return partOne + keyword + partTwo;
    }
}
