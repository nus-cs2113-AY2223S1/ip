package duke.exception;

import duke.Ui;

public class MissingTimeDukeException extends DukeException{
    public MissingTimeDukeException() {

    }

    @Override
    public void handle(){
        Ui.outputWithLines("Please specify a time\n" +
                "Follow this format:\n" +
                "event/deadline {description} /{time}\n" +
                "Example: event find a girlfriend /never");
    }
}
