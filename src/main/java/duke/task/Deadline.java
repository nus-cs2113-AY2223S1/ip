package duke.task;

import java.io.FileNotFoundException;

import duke.exception.DukeException;

public class Deadline extends Task {

    public String by;

    // split the description into details and by and store return a tuple of the two
    // public static String[] splitDeadlineDescription(String description) throws DukeException {
    //     String[] split = description.split(" /by ");
    //     if (split.length != 2) {
    //         throw new InvalidDeadlineInputException("Please enter a valid deadline");
    //     }
    //     return split;
    // }


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}