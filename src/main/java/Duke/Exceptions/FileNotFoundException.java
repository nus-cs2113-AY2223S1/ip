package Duke.Exceptions;

public class FileNotFoundException extends Exception {
    private static final String ERROR_MESSAGE = "File is not found! Give me a moment to create it! ";

    public FileNotFoundException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
