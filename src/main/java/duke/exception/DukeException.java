package duke.exception;

/**
 * <code>DukeException</code> is the abstract base class for every self-defined exception in the application.
 */
public abstract class DukeException extends Exception {
    /**
     * Returns the error message of the exception for user interface output display.
     *
     * @return A string storing the error message.
     */
    public abstract String getMessage();

    /**
     * Returns the prefix of the error message to be appended to the front of every error message.
     *
     * @return A string storing the prefix of the error message.
     */
    public String getMessagePrefix() {
        return " :( OOPS!!! ";
    }
}
