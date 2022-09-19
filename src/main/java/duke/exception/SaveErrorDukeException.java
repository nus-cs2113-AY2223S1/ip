package duke.exception;

import duke.Ui;

public class SaveErrorDukeException extends DukeException {

    public SaveErrorDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("Error with saving task");
    }
}
