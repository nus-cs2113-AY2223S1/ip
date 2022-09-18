package duke;

/**
 * Exceptions for the duke application.
 */
public class DukeException extends Exception {
    /**
     * Creates a new exception.
     * 
     * @param message The message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Formats the exception for display.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
