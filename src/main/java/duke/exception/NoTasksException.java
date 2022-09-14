package duke.exception;

public class NoTasksException extends DukeException {

    public NoTasksException() {
        super();
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! You don't have any tasks yet. Why not try creating some?";
    }
}
