package duke.exceptions;

/**
 * Represents an error when user inputs an invalid command.
 */
public class InvalidCommandFormatException extends Exception {
    private static final String ERROR_MESSAGE = "Invalid Command Format! ";

    public InvalidCommandFormatException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return (ERROR_MESSAGE);
    }
}