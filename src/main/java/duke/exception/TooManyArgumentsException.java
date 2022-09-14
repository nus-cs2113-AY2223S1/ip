package duke.exception;

public class TooManyArgumentsException extends DukeException {

    public TooManyArgumentsException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You have provided too many arguments.";
    }
}
