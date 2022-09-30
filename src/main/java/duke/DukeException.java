package duke;

/**
 * Represents error message when user input/task list info is valid
 */
public class DukeException extends Exception{
    private final String errorMessage;
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
