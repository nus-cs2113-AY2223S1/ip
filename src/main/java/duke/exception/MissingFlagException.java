package duke.exception;

public class MissingFlagException extends Exception {

    @Override
    public String toString() {
        return "Missing flag, try again :(";
    }
}
