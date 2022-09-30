package duke.exceptions;

public class MissingDateException extends Exception {
    private static final String MESSAGE = "Missing deadline date. Please type again.";

    public String getMessage() {
        return MESSAGE;
    }

}

