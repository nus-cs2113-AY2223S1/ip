package duke.exceptions;

public class MissingArgumentException extends ArrayIndexOutOfBoundsException {
    private static final String MESSAGE = "Missing argument.";

    public String getMessage() {
        return MESSAGE;
    }
}
