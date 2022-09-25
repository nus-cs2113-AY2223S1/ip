package duke.exception;

/**
 * <code>TodoMissingDescriptionException</code> represents the exception triggered
 * when a to-do task given by the user does not contain a valid description.
 */
public class TodoMissingDescriptionException extends DukeException {

    /**
     * Concatenates the error message prefix and error message, and returns it.
     *
     * @return The full error message
     */
    @Override
    public String getMessage() {
        return super.getMessagePrefix() + ErrorMessage.TODO_MISSING_DESCRIPTION_ERROR_MESSAGE;
    }
}
