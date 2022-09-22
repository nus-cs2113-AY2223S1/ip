package duke.exception;

import duke.Ui;

/**
 * Represents exception with missing save file
 */
public class MissingDataFileDukeException extends DukeException {
    public MissingDataFileDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("data file not found");
    }
}
