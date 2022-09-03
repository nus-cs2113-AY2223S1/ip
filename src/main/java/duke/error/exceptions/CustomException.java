package duke.error.exceptions;

/**
 * Custom Exception that forces a {@code getExceptionMessage()} method.
 */
public abstract class CustomException extends Exception {
    /**
     * Message to be used in dialog box
     *
     * @return message string
     */
    public abstract String getExceptionMessage();
}
