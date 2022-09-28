package duke.error.exceptions;

/**
 * Custom Exception that forces implementation of a {@link DukeException#getExceptionMessage} method.
 */
public abstract class DukeException extends Exception {
    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    public abstract String getExceptionMessage();
}
