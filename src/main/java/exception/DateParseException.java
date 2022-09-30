package exception;

/**
 * Exception if arguments provided for corresponding commands is insufficient or invalid
 */
public class DateParseException extends Exception {
    private String MESSAGE =
            "The input date must be given in the format: YYYY-MM-DD. \nInvalid date detected. Please try again.";
    /**
     * Returns corresponding error message when called
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
