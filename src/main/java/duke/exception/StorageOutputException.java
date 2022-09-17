package duke.exception;

public class StorageOutputException extends DukeException {

    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.STORAGE_OUTPUT_ERROR_MESSAGE;
    }
}