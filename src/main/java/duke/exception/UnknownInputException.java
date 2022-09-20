package duke.exception;

public class UnknownInputException extends DukeException {
    public UnknownInputException() {
        this.ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-( ";
    }
}
