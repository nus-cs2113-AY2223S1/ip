package duke;

/**
 * Signals an error due to invalid command
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }

}
