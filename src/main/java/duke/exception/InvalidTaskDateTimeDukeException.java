package duke.exception;

import duke.Ui;

/**
 * Represents exception with task date & time
 */
public class InvalidTaskDateTimeDukeException extends DukeException {
    public InvalidTaskDateTimeDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("Please specify a date (and time)\n" +
                "Follow this format:\n" +
                "\n" +
                "on/before/after {date in dd-mm-yyyy}\n" +
                "Example: after 22-7-2026\n" +
                "\n" +
                "event/deadline {description} /{date in dd-mm-yyyy} {time (optional) in hhmm 24hr notation}\n" +
                "Example: event go on a date /1-1-2066");
    }
}
