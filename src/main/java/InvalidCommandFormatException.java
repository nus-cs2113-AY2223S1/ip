public class InvalidCommandFormatException extends Exception {
    private static final String ERROR_MESSAGE = "Invalid Command Format! ";
    protected static String correctFormatMessage;

    public InvalidCommandFormatException(String correctFormatMessage) {
        super(ERROR_MESSAGE);
        this.correctFormatMessage = correctFormatMessage;
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE + correctFormatMessage);
    }
}