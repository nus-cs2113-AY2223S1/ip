package duke.exception;

/**
 * Represents an exception of Duke system,
 * when cmd given is unknown
 */
public class IllegalArgsTypeException extends DukeException {
    @Override
    public String getMessage() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
