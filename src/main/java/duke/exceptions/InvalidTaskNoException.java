package duke.exceptions;

/**
 * Exception called when the task number is outside the range of the arraylist
 */
public class InvalidTaskNoException extends Exception {
    private static final String MESSAGE = "Task number does not exist. Please refer to the list and try again.";

    public String getMessage() {
        return MESSAGE;
    }
}
