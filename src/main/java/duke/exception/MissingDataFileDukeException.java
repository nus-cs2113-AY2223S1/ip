package duke.exception;

import duke.Ui;

public class MissingDataFileDukeException extends DukeException{
    public MissingDataFileDukeException() {
    }

    @Override
    public void handle() {
        Ui.outputWithLines("data file not found");
    }
}
