package duke.exceptions;

public class DoneFormatException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Done command must follow the format:done <index>";
    }
}
