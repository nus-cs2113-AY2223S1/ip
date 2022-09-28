package Duke.Exceptions;

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
