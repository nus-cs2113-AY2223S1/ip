package duke.exception;

import duke.Ui;

/**
 * Represents exception where error is encountered when saving tasks
 */
public class SaveErrorDukeException extends DukeException {

    public SaveErrorDukeException() {
    }

    /**
     * Informs user of the exception
     */
    @Override
    public void handle() {
        Ui.outputWithLines("Error with saving task");
    }
}
