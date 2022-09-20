package duke.exception;

public class DukeIOException extends DukeException{
    public DukeIOException() {
        this.ERROR_MESSAGE = "Sorry, IO Error occurred when running duke :(";
    }
}