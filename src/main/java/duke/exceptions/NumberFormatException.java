package duke.exceptions;

public class NumberFormatException extends Exception {
    private static final String MESSAGE = "Please key in integer instead.";

    public String getMessage() {
        return MESSAGE;
    }
}
