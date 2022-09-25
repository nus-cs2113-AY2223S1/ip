package exception;

/**
 * Exception if index provided in command call is > size of taskList at the time
 */
public class TaskDoesNotExistException extends Exception {
    private String MESSAGE = "Task does not exist. Please try again.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
