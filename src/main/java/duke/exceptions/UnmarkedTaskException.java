package duke.exceptions;

public class UnmarkedTaskException extends Exception{
    protected final String MESSAGE = "☹ OOPS!!! Task is already unmarked";

    /**
     * method to get error message
     * @return string containing error message
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
