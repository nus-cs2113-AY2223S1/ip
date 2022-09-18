package duke.exception;

import duke.Ui;

public class DukeException extends Exception {

    public DukeException() {
    }

    public void handle() {
        Ui.outputWithLines("Invalid input. Boo! Please type help for help");
    }

}
