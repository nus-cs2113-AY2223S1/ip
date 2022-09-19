package duke.exception;

import duke.Ui;

/**
 * Represents exception when saving tasks
 */
public class SaveErrorDukeException extends DukeException {

    public SaveErrorDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("Error with saving task");
    }
}
