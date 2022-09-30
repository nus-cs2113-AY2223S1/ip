package duke.exceptions;

public class InvalidArgumentException extends Exception {
    private static final String MESSAGE = "Keyword is incorrect. Please try again.";

    public String getMessage() {
        return MESSAGE;
    }
}
