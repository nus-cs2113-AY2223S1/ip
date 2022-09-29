package exception;

/**
 * Exception if input is an invalid command
 */
public class InvalidCommandException extends Exception {
    private String MESSAGE = "Invalid command. Please try again.";

    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
