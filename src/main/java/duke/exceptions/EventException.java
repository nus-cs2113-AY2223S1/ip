package duke.exceptions;
/**
 * Thrown when EventCommand does not follow the format: event [description] /at [time]
 */
public class EventException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Event command must follow the format <description> /at <time/date>";
    }
}
