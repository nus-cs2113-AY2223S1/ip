package Duke.Exceptions;

/**
 * Represents an error when the task list is empty and the user tries to access it.
 */
public class TaskListEmptyException extends Exception {
    private static final String ERROR_MESSAGE = "Task List is empty!";

    public TaskListEmptyException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}