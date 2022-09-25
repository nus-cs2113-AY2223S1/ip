package duke.exception;

/**
 * <code>InvalidDateFormatException</code> represents the exception triggered
 * when a datetime given by the user is not in the supported format.
 */
public class InvalidDateTimeFormatException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_DATE_TIME_FORMAT_ERROR_MESSAGE;
    }
}
