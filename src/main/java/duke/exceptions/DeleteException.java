package duke.exceptions;

/**
 * Thrown when DeleteCommand does not follow the format: delete [index]
 */
public class DeleteException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Delete command must follow the format: delete <index>";
    }
}
