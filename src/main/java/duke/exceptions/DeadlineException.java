package duke.exceptions;

/**
 * Thrown when DeadlineCommand does not follow the format: deadline [description] /by [time]
 */
public class DeadlineException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Deadline command must follow the format <description> /by <time/date> ";
    }
}
