package duke.exception;

/**
 * <code>EventMissingDescriptionException</code> represents the exception triggered
 * when an event task given by the user does not contain a valid description.
 */
public class EventMissingDescriptionException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_DESCRIPTION_ERROR_MESSAGE;
    }
}
