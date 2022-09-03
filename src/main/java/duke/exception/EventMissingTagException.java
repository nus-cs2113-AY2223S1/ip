package duke.exception;

public class EventMissingTagException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_TAG_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
