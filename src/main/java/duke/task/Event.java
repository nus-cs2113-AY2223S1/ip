package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event-type task.
 */
public class Event extends Task {
    protected LocalDate atDate;
    protected String atTime;

    /**
     * Constructs constructor for Event task and initializes task name, date and time.
     */
    public Event(String description, LocalDate atDate, String atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Retrieves event date.
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Contains full description of event task.
     */
    @Override
    public String toString() {
        String date = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        boolean hasTime = !atTime.isEmpty();
        String holder = hasTime ? ", " : "";
        return "[E]" + super.toString() + " (at: " + date + holder + atTime + ")";
    }
}
