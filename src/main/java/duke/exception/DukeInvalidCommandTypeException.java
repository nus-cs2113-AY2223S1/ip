package duke.exception;

public class DukeInvalidCommandTypeException extends Exception {

    @Override
    public String toString() {
        return "Invalid command, try again :(";
    }
}
