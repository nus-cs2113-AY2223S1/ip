package exception;

/**
 * Exception if arguments provided for corresponding commands is insufficient or invalid
 */
public class DateParseException extends Exception {
    private String MESSAGE =
            "Invalid date detected. The input date must be given in the format: YYYY-MM-DD. Please try again.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
