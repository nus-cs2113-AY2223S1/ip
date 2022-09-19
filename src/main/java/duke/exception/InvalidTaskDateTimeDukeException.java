package duke.exception;

import duke.Ui;

/**
 * Represents exception with task date & time
 */
public class InvalidTaskDateTimeDukeException extends DukeException{
    public InvalidTaskDateTimeDukeException() {
    }


    @Override
    public void handle(Ui ui){
        ui.output("Please specify a date\n" +
                "Follow this format:\n" +
                "event/deadline {description} /{date in dd-mm-yyyy} {time(optional) in hhmm 24hr notation}\n" +
                "Example: event find a girlfriend /1-1-2066 1600");
    }
}
