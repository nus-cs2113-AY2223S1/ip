package duke.exception;

public class AbsentArgsFlagException extends DukeException {

    public String getMessage(String task_type) {
        switch (task_type) {
        case "D":
            return "The description of a deadline should contain /by.";
        case "E":
            return "The description of a event should contain /at.";
        default:
            return "";
        }
    }
}
