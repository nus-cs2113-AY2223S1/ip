package duke.exception;

/**
 * Represents an exception of Duke system
 * when description of event lacks '/at',
 * or of deadline lacks '/by'
 */
public class AbsentArgsFlagException extends DukeException {
    public String getMessage() {
        return "The description of a deadline(event) should contain /by(/at).";
    }
}
