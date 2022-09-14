package duke.exception;

public class MissingTimeException extends DukeException {

    public MissingTimeException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You did not provide the time for the command.";
    }
}
