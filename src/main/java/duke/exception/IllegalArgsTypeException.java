package duke.exception;

public class IllegalArgsTypeException extends DukeException {
    @Override
    public String getMessage() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
