package duke.exceptions;
/**
 * Thrown when DoneCommand does not follow the format: done [index]
 */
public class DoneFormatException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command must follow the format:done <index>";
    }
}
