package exception;

public class NotEnoughArgumentsException extends Exception {
    private String MESSAGE = "Not enough arguments entered. Please try again.";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
