package duke.exception;

public class DeadlineMissingDescriptionException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_DESCRIPTION_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
