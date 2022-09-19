package duke.exception;

import duke.Ui;

/**
 * Represents exception where error is encountered when loading tasks
 */
public class LoadErrorDukeException extends DukeException{

    public LoadErrorDukeException() {
    }

    /**
     * Informs user of the exception
     */
    @Override
    public void handle(){
        Ui.outputWithLines("Error with loading task");
    }
}
