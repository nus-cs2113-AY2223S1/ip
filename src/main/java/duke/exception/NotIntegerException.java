package duke.exception;

public class NotIntegerException extends DukeException {
    private String message;

    public NotIntegerException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + message + " is not an integer. Please try again with an integer.";
    }
}
