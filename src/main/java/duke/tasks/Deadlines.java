package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which includes a date and time for which it is due.
 */
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

    /**
     * Constructor for a new deadline task.
     *
     * @param taskName A name or description given to the task.
     * @param toBeDoneBy A string that represents any deadlines for the task.
     */
    public Deadlines(String taskName, String toBeDoneBy, LocalDate date, LocalTime time) {
        super(taskName);
        this.toBeDoneBy = toBeDoneBy;
        this.date = date;
        this.time = time;
        this.type = "D";
    }

    /**
     * Overrides <code>toString()</code> method for purpose of the program.
     * @return A string representing the deadline task.
     */
    public String toString() {
        String done = this.getIsDone() ? "[X] " : "[ ] ";
        String dateTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + date.getDayOfWeek() + ", " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        return "[D]" + done + this.getTaskName() + " (by: " + dateTime + ")";
    }
}