package duke.exceptions;

public class DeadlineException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Deadline command must follow the format <description> /by <time/date> ";
    }
}
