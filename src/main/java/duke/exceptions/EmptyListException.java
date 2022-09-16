package duke.exceptions;

public class EmptyListException extends EventException {
    @Override
    public String getMessage() {
        return "OOPS!!! Task list is empty";
    }
}
