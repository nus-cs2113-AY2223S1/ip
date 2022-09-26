package duke.exception;

public class UnrecognisedCommandException extends DukeException {

    public UnrecognisedCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Unrecognised type of task encountered while saving.";
    }
}
