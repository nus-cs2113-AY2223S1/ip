package duke.exception;

import duke.Ui;

/**
 * Represents exceptions expected by Duke
 */
public class DukeException extends Exception {

    public DukeException() {
    }


    /**
     * Informs user of the exception
     * Displays valid sample inputs if applicable
     * @param ui Ui for printing message
     */
    public void handle(Ui ui) {
        ui.output("Invalid input. Boo! Please type help for help");
    }

}
