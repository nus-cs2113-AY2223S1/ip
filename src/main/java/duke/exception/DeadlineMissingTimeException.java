package duke.exception;

/**
 * <code>DeadlineMissingTimeException</code> represents the exception triggered
 * when a deadline task given by the user does not contain a valid date time.
 */
public class DeadlineMissingTimeException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_TIME_ERROR_MESSAGE;
    }
}
