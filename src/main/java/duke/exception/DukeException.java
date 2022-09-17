package duke.exception;

public abstract class DukeException extends Exception {
    public abstract String getMessage();

    public String getMessagePrefix() {
        return " :( OOPS!!! ";
    }
}
