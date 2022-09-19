package duke.exception;

public class InvalidDateTimeFormatException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_DATE_TIME_FORMAT_ERROR_MESSAGE;
    }
}
