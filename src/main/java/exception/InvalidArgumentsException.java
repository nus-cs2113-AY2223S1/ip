package exception;

public class InvalidArgumentsException extends Exception {
    private String MESSAGE = "Invalid arguments detected. Please try again.";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
