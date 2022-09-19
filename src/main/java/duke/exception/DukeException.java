package duke.exception;

import duke.Ui;

/**
 * Represents exceptions expected to be encountered by Duke
 */
public class DukeException extends Exception {

    public DukeException() {
    }

    /**
     * Informs user of the exception
     */
    public void handle() {
        Ui.outputWithLines("Invalid input. Boo! Please type help for help");
    }

}
