package duke.exception;

public class DukeIOException extends DukeException{

    /**
     * Constructor of DukeIOException
     */
    public DukeIOException() {
        this.ERROR_MESSAGE = "Sorry, IO Error occurred when running duke :(";
    }
}