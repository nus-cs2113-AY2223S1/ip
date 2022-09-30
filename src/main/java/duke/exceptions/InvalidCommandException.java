package duke.exceptions;

/**
 * Exception called when the user input command is not recognised
 */
public class InvalidCommandException extends Exception{
    private static final String MESSAGE = "Invalid command.";
    public String getMessage() {
        return MESSAGE;
    }
}
