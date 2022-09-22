package duke.exception;

import duke.Ui;

/**
 * Represents exception with missing task name
 */
public class MissingTaskNameDukeException extends DukeException {

    public MissingTaskNameDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("Please describe the task\n" +
                "Follow this format:\n" +
                "\n" +
                "todo {description}\n" +
                "Example: todo find a girlfriend\n" +
                "\n" +
                "event/deadline {description} /{date in dd-mm-yyyy} {time(optional) in hhmm 24hr notation}\n" +
                "Example: deadline make a new friend /31-12-2021 2359");
    }
}
