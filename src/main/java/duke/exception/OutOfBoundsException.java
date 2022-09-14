package duke.exception;

public class OutOfBoundsException extends DukeException {

    public OutOfBoundsException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You don't have that number of tasks!";
    }
}
