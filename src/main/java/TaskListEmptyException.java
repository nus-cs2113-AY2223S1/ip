
public class TaskListEmptyException extends Exception {
    private static final String ERROR_MESSAGE = "Task List is empty! ";

    public TaskListEmptyException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}