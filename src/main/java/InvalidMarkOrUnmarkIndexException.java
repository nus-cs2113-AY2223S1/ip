public class InvalidMarkOrUnmarkIndexException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_MARK_OR_UNMARK_INDEX_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
