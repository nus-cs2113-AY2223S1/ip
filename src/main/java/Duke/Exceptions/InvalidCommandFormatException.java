package Duke.Exceptions;

public class InvalidCommandFormatException extends Exception {
    private static final String ERROR_MESSAGE = "Invalid Command Format! ";
    protected static String correctFormatMessage;

    public InvalidCommandFormatException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}