package duke.exception;

public class AbsentArgsFlagException extends DukeException {
    public String getMessage() {
        return "The description of a deadline(event) should contain /by(/at).";
    }
}
