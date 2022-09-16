package duke.exceptions;

public class DoNothingException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Command not found";
    }
}
