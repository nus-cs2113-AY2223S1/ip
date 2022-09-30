package duke.exceptions;

/**
 * Exception is called when there is insufficient/no task description.
 */
public class MissingArgumentException extends Exception {
    private static final String MESSAGE = "Missing description. Please type again.";

    public String getMessage() {
        return MESSAGE;
    }
}
