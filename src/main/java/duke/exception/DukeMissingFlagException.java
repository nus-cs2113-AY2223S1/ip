package duke.exception;

public class DukeMissingFlagException extends Exception {

    @Override
    public String toString() {
        return "Missing flag, try again :(";
    }
}
