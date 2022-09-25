package duke.exception;

public class IllegalArgsNumException extends DukeException {
    @Override
    public String getMessage() {
        return "The description cannot be empty.";
    }
}
