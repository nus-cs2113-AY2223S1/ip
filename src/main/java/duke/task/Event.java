package duke.task;

import java.io.FileNotFoundException;

import duke.exception.DukeException;

public class Event extends Task {

    public String at;

    // public static String[] splitEventDescription(String description) throws DukeException {
    //     String[] split = description.split(" /at ");
    //     if (split.length != 2) {
    //         throw new InvalidEventInputException("Please enter a valid deadline");
    //     }
    //     return split;
    // }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}