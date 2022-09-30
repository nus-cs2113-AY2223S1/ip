package exceptions;

public class InvalidDeadlineException extends Exception {
    @Override
    public String getMessage() {
        return "Missing /by field. Try again.";
    }
}
