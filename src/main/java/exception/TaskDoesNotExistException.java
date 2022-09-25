package exception;

public class TaskDoesNotExistException extends Exception {
    private String MESSAGE = "Task does not exist. Please try again.";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
