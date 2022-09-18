package duke.exception;

import duke.Ui;

public class InvalidTaskNumberDukeException extends DukeException {

    public InvalidTaskNumberDukeException() {
    }

    @Override
    public void handle() {
        Ui.outputWithLines("Invalid task number: task does not exist\n" +
                "Please try again");
    }
}
