package duke.exception;

public class StorageInitializationException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.STORAGE_INITIALIZATION_ERROR_MESSAGE + super.getMessagePostfix();
    }
}