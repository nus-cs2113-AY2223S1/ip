package duke.exception;

public class DukeException extends Exception {

    private String message;

    /**
     * Constructor for DukeException
     *
     * @param message The error message to be displayed
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }
}
