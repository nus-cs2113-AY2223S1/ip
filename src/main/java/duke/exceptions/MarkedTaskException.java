package duke.exceptions;

public class MarkedTaskException extends Exception{
    protected final String MESSAGE = "☹ OOPS!!! Task is already marked";

    /**
     * method to get error message
     * @return string containing error message
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
