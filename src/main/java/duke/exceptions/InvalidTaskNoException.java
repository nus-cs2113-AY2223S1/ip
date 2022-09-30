package duke.exceptions;

public class InvalidTaskNoException extends Exception {
    private static final String MESSAGE = "Task number does not exist. Please refer to the list and try again.";

    public String getMessage() {
        return MESSAGE;
    }
}
