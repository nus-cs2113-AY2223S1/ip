package duke.exception;

import duke.Ui;

/**
 * Represents error where save file is not found
 */
public class MissingDataFileDukeException extends DukeException{
    public MissingDataFileDukeException() {
    }

    /**
     * Informs user of the exception
     */
    @Override
    public void handle() {
        Ui.outputWithLines("data file not found");
    }
}
