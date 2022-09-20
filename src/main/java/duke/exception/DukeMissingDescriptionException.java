package duke.exception;

public class DukeMissingDescriptionException extends Exception {

    @Override
    public String toString() {
        return "EXCEPTION: Missing Description\n"
                + "Please type in a task description";
    }
}
