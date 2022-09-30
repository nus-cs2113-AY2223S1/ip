package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task which includes a date and time for which it is happening.
 */
public class Events extends Task {
    private String happeningAt;
    private LocalDate date;
    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public String getHappeningAt() {
        return happeningAt;
    }

    /**
     * Constructor for a new deadline task.
     *
     * @param taskName A name or description given to the task.
     * @param happeningAt A string that represents when the event is happening.
     */
    public Events(String taskName, String happeningAt, LocalDate date, LocalTime time) {
        super(taskName);
        this.happeningAt = happeningAt;
        this.date = date;
        this.time = time;
        this.type = "E";
    }

    /**
     * Returns a string describing the event.
     *
     * @return A string representing the event task.
     */
    public String toString() {
        String done = this.getIsDone() ? "[X] " : "[ ] ";
        String dateTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + date.getDayOfWeek() + ", " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        return "[E]" + done + this.getTaskName() + " (at: " + dateTime + ")";
    }
}