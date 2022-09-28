package duke.exceptions;
/** Thrown when commands are not recognised */
public class DoNothingException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Command not found";
    }
}
