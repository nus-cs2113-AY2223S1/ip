package duke.exception;

public class DukeUnknownInputException extends DukeException {

    /**
     * Constructor of DukeUnknownInputException
     */
    public DukeUnknownInputException() {
        this.ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-( ";
    }
}
