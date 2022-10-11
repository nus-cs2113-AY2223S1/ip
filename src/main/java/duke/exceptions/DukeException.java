package duke.exceptions;

/**
 * An exception that occurs when the parse method in Parser class takes in a String as command that is
 * not part of any acceptable command
 */
public class DukeException extends Exception {
    protected final String ERROR_MESSAGE = "Nothing makes any sense";

    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
