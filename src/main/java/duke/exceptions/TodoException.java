package duke.exceptions;

public class TodoException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Todo command must follow the format:todo <description>";
    }
}
