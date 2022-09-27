package duke.exception;

/**
 * <code>NoTasksException</code> is thrown when a user tries to do something with a task
 * when there are no tasks in the list.
 */
public class NoTasksException extends DukeException {

    /**
     * Constructor of <code>NoTasksException</code>.
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
        return "There are no tasks stored. Why not try creating some?";
    }
}
