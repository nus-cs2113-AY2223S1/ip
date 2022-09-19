package duke.exception;

/**
 * <code>StorageOutputException</code> represents the exception triggered
 * when a file operation error occurs when the application attempts to store the changed task list
 * by writing or appending into the file.
 */
public class StorageOutputException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.STORAGE_OUTPUT_ERROR_MESSAGE;
    }
}