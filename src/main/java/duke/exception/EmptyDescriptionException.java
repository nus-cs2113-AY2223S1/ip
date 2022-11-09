package duke.exception;

/**
 * This class handles the exception when there is an empty description
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String s) {
        super(s);
    }
}
