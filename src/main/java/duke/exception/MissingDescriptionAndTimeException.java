package duke.exception;

public class MissingDescriptionAndTimeException extends DukeException {

    public MissingDescriptionAndTimeException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You did not provide both a description and time for the task!";
    }
}
