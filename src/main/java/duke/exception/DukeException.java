package duke.exception;

/**
 * <code>DukeException</code> is the abstract base class for exceptions.
 */
public abstract class DukeException extends Exception {

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    public abstract String getMessage();
}
