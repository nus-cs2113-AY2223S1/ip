package Duke.Exceptions;

/**
 * Represents an error when the task number that user inputs is not within the taskList.
 */
public class TaskNumberOutOfBoundsException extends Exception {
    private static final String ERROR_MESSAGE = "Task Number specified is not in the list!";

    public TaskNumberOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}