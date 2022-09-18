package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private String toBeDoneBy;
    private LocalDate date;
    private LocalTime time;

    public String getToBeDoneBy() {
        return toBeDoneBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public Deadlines(String taskName, String toBeDoneBy, LocalDate date, LocalTime time) {
        super(taskName);
        this.toBeDoneBy = toBeDoneBy;
        this.date = date;
        this.time = time;
        this.type = "D";
    }

    public String toString() {
        String done = this.getIsDone() ? "[X] " : "[ ] ";
        String dateTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + date.getDayOfWeek() + ", " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        return "[D]" + done + this.getTaskName() + " (by: " + dateTime + ")";
    }
}