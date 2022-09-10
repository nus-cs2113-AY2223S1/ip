package duke.exceptions;

public class UnmarkedTaskException extends Exception{
    protected final String MESSAGE = "☹ OOPS!!! Task is already unmarked";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
