package exception;

/**
 * Exception if insufficient arguments are provided for certain command calls
 */
public class NotEnoughArgumentsException extends Exception {
    private String MESSAGE = "Not enough arguments entered. Please try again.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
