package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline-type task.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byTime;

    /**
     * Constructs constructor for Deadline task and initializes task name, date and time.
     */
    public Deadline(String description, LocalDate byDate, String byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Retrieves deadline date.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Contains full description of deadline task.
     */
    @Override
    public String toString() {
        String date = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        boolean hasTime = !byTime.isEmpty();
        String holder = hasTime ? ", " : "";
        return "[D]" + super.toString() + " (by: " + date + holder + byTime + ")";
    }
}