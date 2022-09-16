package duke.exceptions;

public class DeleteException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Delete command must follow the format: delete <index>";
    }
}
