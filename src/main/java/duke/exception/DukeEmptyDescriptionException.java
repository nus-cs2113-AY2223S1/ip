package duke.exception;

public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Constructor of DukeEmptyDescriptionException
     */
    public DukeEmptyDescriptionException() {
        this.ERROR_MESSAGE = "OOPS!!! The description cannot be empty :(";
    }
}
