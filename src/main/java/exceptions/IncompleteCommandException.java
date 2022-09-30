package exceptions;

public class IncompleteCommandException extends Exception {
    @Override
    public String getMessage() {
        return "incomplete 'find' command. Usage: find {keyword}.";
    }
}
