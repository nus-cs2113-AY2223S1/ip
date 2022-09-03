public class TodoMissingDescriptionException extends DukeException{

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.TODO_MISSING_DESCRIPTION_ERROR_MESSAGE + super.getMessagePostfix();
    }
}
