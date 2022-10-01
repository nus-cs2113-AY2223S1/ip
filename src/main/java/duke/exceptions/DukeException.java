package duke.exceptions;

public class DukeException extends Exception {
    protected final String MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * method to get error message
     * @return string containing error message
     */
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

