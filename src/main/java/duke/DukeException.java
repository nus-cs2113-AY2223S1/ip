package duke;

/**
 * Define exceptions thrown by {@link Duke#}.
 */
public abstract class DukeException extends Exception {
    private String message;

    /**
     * Initializes a new exception.
     */
    public DukeException() {
    }

    /**
     * Initializes a new exception with a message.
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
