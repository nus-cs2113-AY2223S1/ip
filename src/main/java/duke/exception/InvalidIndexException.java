package duke.exception;

public class InvalidIndexException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_INDEX_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
