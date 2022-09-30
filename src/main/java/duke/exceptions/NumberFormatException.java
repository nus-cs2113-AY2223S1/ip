package duke.exceptions;

/**
 * Exception called when the task number inputted is not in integer value
 */
public class NumberFormatException extends Exception {
    private static final String MESSAGE = "Please key in integer instead.";

    public String getMessage() {
        return MESSAGE;
    }
}
