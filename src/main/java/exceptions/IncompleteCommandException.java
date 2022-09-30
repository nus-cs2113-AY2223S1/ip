package exceptions;

public class IncompleteCommandException extends Exception {
    
    /**
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "incomplete 'find' command. Usage: find {keyword}.";
    }
}
