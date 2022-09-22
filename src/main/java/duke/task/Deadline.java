package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    public String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}