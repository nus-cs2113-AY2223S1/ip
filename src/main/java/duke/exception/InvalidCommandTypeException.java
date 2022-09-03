package duke.exception;

public class InvalidCommandTypeException extends Exception {

    @Override
    public String toString() {
        return "Invalid command, try again :(";
    }
}
