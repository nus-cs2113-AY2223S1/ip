package duke.exception;

public class EventMissingDescriptionException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.EVENT_MISSING_DESCRIPTION_ERROR_MESSAGE;
    }
}
