package duke.exception;

public class NoSuchCommandException extends DukeException {

    public NoSuchCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You did not provide a valid command.";
    }
}
