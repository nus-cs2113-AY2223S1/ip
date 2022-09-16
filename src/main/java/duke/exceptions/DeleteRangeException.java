package duke.exceptions;

public class DeleteRangeException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Delete command is out of range of the number of tasks.";
    }
}
