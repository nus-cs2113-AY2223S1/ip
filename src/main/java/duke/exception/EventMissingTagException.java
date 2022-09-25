package duke.exception;

/**
 * <code>EventMissingTagException</code> represents the exception triggered
 * when an event task given by the user does not contain a valid " /at " tag.
 */
public class EventMissingTagException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_TAG_ERROR_MESSAGE;
    }
}
