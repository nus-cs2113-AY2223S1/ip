package duke.exception;

/**
 * <code>InvalidIndexException</code> represents the exception triggered
 * when a task index given by the user is not a valid integer
 * or is not within the valid range of the task list.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Concatenate the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.INVALID_INDEX_ERROR_MESSAGE;
    }
}
