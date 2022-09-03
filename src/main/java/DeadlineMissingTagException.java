public class DeadlineMissingTagException extends DukeException{

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.DEADLINE_MISSING_TAG_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
