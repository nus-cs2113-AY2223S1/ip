package exceptions;

public class InvalidDeadlineException extends Exception {
    
    /**
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "Missing /by field. Try again.";
    }
}
