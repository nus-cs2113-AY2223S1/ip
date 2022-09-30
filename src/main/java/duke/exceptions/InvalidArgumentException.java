package duke.exceptions;

/**
 * Exception called when "/by" or "/at" keyword is not used
 */
public class InvalidArgumentException extends Exception {
    private static final String MESSAGE = "Keyword is incorrect. Please try again.";

    public String getMessage() {
        return MESSAGE;
    }
}
