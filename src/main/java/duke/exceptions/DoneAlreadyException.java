package duke.exceptions;
/**
 * Thrown when index in DoneCommand is already marked as done
 */
public class DoneAlreadyException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command is out of range of the number of tasks.";
    }
}
