package duke.exception;

/**
 * <code>InvalidCommandException</code> represents the exception triggered
 * when a command given by the user is not a command supported by the application.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_COMMAND_ERROR_MESSAGE;
    }
}
