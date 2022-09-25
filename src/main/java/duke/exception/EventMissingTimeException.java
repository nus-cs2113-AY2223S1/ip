package duke.exception;

/**
 * <code>EventMissingTimeException</code> represents the exception triggered
 * when an event task given by the user does not contain a valid date time.
 */
public class EventMissingTimeException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_TIME_ERROR_MESSAGE;
    }
}
