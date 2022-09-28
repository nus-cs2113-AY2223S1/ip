package duke.exception;

/**
 * <code>MissingArgumentException</code> is thrown when the user did not provide
 * the necessary arguments for the execution of commands.
 */
public class MissingArgumentException extends DukeException {

    private final String missingArgument;

    /**
     * Constructor of <code>MissingArgumentException</code>.
     * Stores the type of command for error message printing.
     *
     * @param missingArgument the missing argument of the command
     */
    public MissingArgumentException(String missingArgument) {
        super();
        this.missingArgument = missingArgument;
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        String messagePartOne = "OOPS!!! Your command is missing the ";
        String messagePartTwo = " argument!";
        return messagePartOne + missingArgument + messagePartTwo;
    }
}
