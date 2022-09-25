package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byTime;

    public Deadline(String description, LocalDate byDate, String byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        String date = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        boolean hasTime = !byTime.isEmpty();
        String holder = hasTime ? ", " : "";
        return "[D]" + super.toString() + " (by: " + date + holder + byTime + ")";
    }
}