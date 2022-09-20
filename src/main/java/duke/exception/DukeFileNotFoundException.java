package duke.exception;

public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException() {
        this.ERROR_MESSAGE = "Sorry, file not found when running duke :(";
    }
}
