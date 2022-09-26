package duke.exception;

/**
 * <code>MissingFlagException</code> is thrown when the user did not input a "/by"
 * or "/at" flag for deadline and event tasks.
 */
public class MissingFlagException extends DukeException {

    private final String flag;

    /**
     * Constructor of <code>MissingFlagException</code>.
     * Stores the flag as "/by" or "/at" depending on whether the command is for deadline
     * or event task creation.
     *
     * @param message type of command
     */
    public MissingFlagException(String message) {
        super();
        if (message.equals("deadline")) {
            this.flag = "/by";
        } else {
            this.flag = "/at";
        }
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        String partOne = "☹ OOPS!!! You did not provide ";
        String partTwo = " in your command.";
        return partOne + flag + partTwo;
    }
}
