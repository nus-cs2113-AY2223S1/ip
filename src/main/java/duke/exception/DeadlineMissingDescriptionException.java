package duke.exception;

/**
 * <code>DeadlineMissingDescriptionException</code> represents the exception triggered
 * when a deadline task given by the user does not contain a valid description.
 */
public class DeadlineMissingDescriptionException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_DESCRIPTION_ERROR_MESSAGE;
    }
}
