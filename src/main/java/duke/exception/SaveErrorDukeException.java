package duke.exception;

import duke.Ui;

public class SaveErrorDukeException extends DukeException {

    public SaveErrorDukeException() {
    }

    @Override
    public void handle() {
        Ui.outputWithLines("Error with saving task");
    }
}
