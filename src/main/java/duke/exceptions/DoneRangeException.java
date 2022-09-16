package duke.exceptions;

public class DoneRangeException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command index is out of the range of the number of tasks.";
    }
}
