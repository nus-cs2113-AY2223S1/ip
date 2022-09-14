package duke.exception;

public class MissingDescriptionException extends DukeException {

    private final String message = "☹ OOPS!!! You did not provide a description for the task!";

    public MissingDescriptionException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
