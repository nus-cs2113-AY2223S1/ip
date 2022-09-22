package duke.task;

import duke.exception.DukeException;

public class Event extends Task {

    public String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}