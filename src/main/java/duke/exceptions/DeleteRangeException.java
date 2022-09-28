package duke.exceptions;

/** Thrown when the index given in the DeleteCommand is out of range */
public class DeleteRangeException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Delete command is out of range of the number of tasks.";
    }
}
