package duke.exceptions;
/** Thrown when taskList is empty */

public class EmptyListException extends EventException {
    @Override
    public String getMessage() {
        return "OOPS!!! Task list is empty";
    }
}
