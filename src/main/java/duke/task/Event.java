package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidEventInputException;

public class Event extends Task {

    public String at;

    public static String[] splitEventDescription(String description) throws DukeException {
        String[] split = description.split(" /at ");
        if (split.length != 2) {
            throw new InvalidEventInputException("Please enter a valid deadline");
        }
        return split;
    }

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}