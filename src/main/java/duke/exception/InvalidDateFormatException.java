package duke.exception;

public class InvalidDateFormatException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_DATE_FORMAT_ERROR_MESSAGE;
    }
}
