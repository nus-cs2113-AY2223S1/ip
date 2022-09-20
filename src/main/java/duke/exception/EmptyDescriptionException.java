package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        this.ERROR_MESSAGE = "OOPS!!! The description cannot be empty :(";
    }
}
