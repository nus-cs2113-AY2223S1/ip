public class DukeException extends Exception {
    protected final String MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

