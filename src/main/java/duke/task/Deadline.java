package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a new Deadline. A <code>Deadline</code> object corresponds to a description of the
 * deadline and the date of the deadline.
 * 
 * @param description the description of the deadline
 * @param by          the date time of the deadline
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats how to print the deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
