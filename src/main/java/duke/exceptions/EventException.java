package duke.exceptions;

public class EventException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Event command must follow the format <description> /at <time/date>";
    }
}
