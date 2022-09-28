package duke.exception;

/**
 * Represents an exception of Duke system,
 * when description of a task is given empty
 */
public class IllegalArgsNumException extends DukeException {
    @Override
    public String getMessage() {
        return "The description cannot be empty.";
    }
}
