package exception;

public class InvalidCommandException extends Exception {
    private String MESSAGE = "Invalid command. Please try again.";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
