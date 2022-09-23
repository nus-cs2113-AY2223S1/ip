package duke;

/**
 * Exceptions for the duke application.
 */
public class DukeException extends Exception {

    private static final String DID_NOT_UNDERSTAND_INDEX_MESSAGE = "Sorry I didn't understand '%s' for parameter INDEX";
    private static final String DID_NOT_UNDERSTAND_MESSAGE = "I don't know what that means :-(";

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

    public static String getDidNotUnderstandIndexMessage(String index) {
        return String.format(DID_NOT_UNDERSTAND_INDEX_MESSAGE, index);
    }

    public static String getDidNotUnderstandMessage() {
        return DID_NOT_UNDERSTAND_MESSAGE;
    }
}
