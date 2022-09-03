package duke.exception;

public class EventMissingTimeException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_TIME_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
