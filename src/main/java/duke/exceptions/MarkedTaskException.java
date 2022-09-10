package duke.exceptions;

public class MarkedTaskException extends Exception{
    protected final String MESSAGE = "☹ OOPS!!! Task is already marked";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
