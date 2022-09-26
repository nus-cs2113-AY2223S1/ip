package duke.exception;

/**
 * <code>OutOfBoundsException</code> is thrown when the user tries to indicate a
 * task number that does not exist or is greater than the number of tasks in the list.
 */
public class OutOfBoundsException extends DukeException {

    /**
     * Constructor of <code>OutOfBoundsException</code>.
     */
    public OutOfBoundsException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! You don't have that number of tasks!";
    }
}
