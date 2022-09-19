package duke.exception;

import duke.Ui;

/**
 * Represents exception where task date & time are invalid
 */
public class MissingTimeDukeException extends DukeException{
    public MissingTimeDukeException() {

    }

    /**
     * Informs user of the exception and displays valid sample input
     */
    @Override
    public void handle(){
        Ui.outputWithLines("Please specify a time\n" +
                "Follow this format:\n" +
                "event/deadline {description} /{time}\n" +
                "Example: event find a girlfriend /never");
    }
}
