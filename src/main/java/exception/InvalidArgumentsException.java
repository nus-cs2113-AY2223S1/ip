package exception;

/**
 * Exception if arguments provided for corresponding commands is insufficient or invalid
 */
public class InvalidArgumentsException extends Exception {
    private String MESSAGE = "Invalid arguments detected. Please try again.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
