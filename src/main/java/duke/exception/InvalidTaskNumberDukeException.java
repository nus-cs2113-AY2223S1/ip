package duke.exception;

import duke.Ui;

/**
 * Represents exception where task number is invalid
 */
public class InvalidTaskNumberDukeException extends DukeException {

    public InvalidTaskNumberDukeException() {
    }

    /**
     * Informs user of the exception
     */
    @Override
    public void handle() {
        Ui.outputWithLines("Invalid task number: task does not exist\n" +
                "Please try again");
    }
}
