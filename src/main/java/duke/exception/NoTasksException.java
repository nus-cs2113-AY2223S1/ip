package duke.exception;

/**
 * <code>NoTasksException()</code> is thrown when the user tries to access a task in the list
 * when there are no tasks in the list.
 */
public class NoTasksException extends DukeException {

    /**
     * Constructor of <code>NoTasksException()</code>.
     */
    public NoTasksException() {
        super();
    }

    /**
     * Returns the error message of the exception for printing.
     *
     * @return a string that is the error message
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! You don't have any tasks yet. Why not try creating some?";
    }
}
