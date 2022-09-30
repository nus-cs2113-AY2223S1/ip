package duke.exceptions;

/**
 * Represents an error when user inputs an invalid type for task number.
 * Task number should be an integer type
 */
public class TaskNumberNotNumberException extends Exception {
    private static final String ERROR_MESSAGE = "Task Number should be an integer!";

    public TaskNumberNotNumberException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}
