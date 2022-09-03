package duke.exception;

public class MissingDescriptionException extends Exception {

    @Override
    public String toString() {
        return "Missing Description, try again :(";
    }
}
