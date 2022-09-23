package duke.exceptions;

public class DukeException extends Exception {
    protected final String error_message = "Nothing makes any sense";

    public String getMessage() {
        return error_message;
    }
}
