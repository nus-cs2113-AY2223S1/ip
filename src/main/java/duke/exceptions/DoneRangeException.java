package duke.exceptions;
/**
 * Thrown when index in DoneCommand is not within bounds of taskList
 */
public class DoneRangeException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command index is out of the range of the number of tasks.";
    }
}
