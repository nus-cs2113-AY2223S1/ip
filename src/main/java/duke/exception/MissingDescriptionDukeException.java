package duke.exception;

import duke.Ui;

public class MissingDescriptionDukeException extends DukeException{

    public MissingDescriptionDukeException() {
    }

    @Override
    public void handle(Ui ui) {ui.output("Please describe the task\n" +
                "Follow this format:\n" +
                "todo {description}\n" +
                "Example: todo find a girlfriend\n" +
                "event/deadline {description} /{date in dd-mm-yyyy} {time(optional) in hhmm 24hr notation}\n" +
                "Example: deadline find a girlfriend /1-1-2022 2359");
    }
}
