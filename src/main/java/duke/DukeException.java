package duke;

public abstract class DukeException extends Exception {
    private String message;

    public DukeException() {
    }

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
