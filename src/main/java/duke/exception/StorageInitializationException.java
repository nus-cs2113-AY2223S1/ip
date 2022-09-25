package duke.exception;

/**
 * <code>StorageInitializationException</code> represents the exception triggered
 * when a file operation error occurs when the application attempts to read the tasks from the file.
 */
public class StorageInitializationException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.STORAGE_INITIALIZATION_ERROR_MESSAGE;
    }
}