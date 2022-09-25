package duke.exception;

/**
 * <code>DeadlineMissingTagException</code> represents the exception triggered
 * when a deadline task given by the user does not contain a valid " /by " tag.
 */
public class DeadlineMissingTagException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_TAG_ERROR_MESSAGE;
    }
}
