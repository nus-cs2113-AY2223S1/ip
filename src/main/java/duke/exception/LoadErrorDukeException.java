package duke.exception;

import duke.Ui;

public class LoadErrorDukeException extends DukeException{

    public LoadErrorDukeException() {
    }

    @Override
    public void handle(){
        Ui.outputWithLines("Error with loading task");
    }
}
