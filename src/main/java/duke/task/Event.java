package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate atDate;
    protected String atTime;

    public Event(String description, LocalDate atDate, String atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        String date = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        boolean hasTime = !atTime.isEmpty();
        String holder = hasTime ? ", " : "";
        return "[E]" + super.toString() + " (at: " + date + holder + atTime + ")";
    }
}
