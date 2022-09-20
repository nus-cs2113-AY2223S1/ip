package duke.exception;

public class DukeMissingDescriptionException extends Exception {

    @Override
    public String toString() {
        return "Missing Description, try again :(";
    }
}
