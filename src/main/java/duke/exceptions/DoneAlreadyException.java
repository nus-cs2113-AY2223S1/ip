package duke.exceptions;

public class DoneAlreadyException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command is out of range of the number of tasks.";
    }
}
