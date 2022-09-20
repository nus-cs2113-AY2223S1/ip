package duke.exception;

public class DukeNotIntegerException extends Exception {

    @Override
    public String toString() {
        return "Not an integer, try again :(";
    }
}
