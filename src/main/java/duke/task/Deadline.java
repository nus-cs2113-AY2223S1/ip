package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline.
 */
public class Deadline extends Task{

    private final LocalDateTime dateAndTime;
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    /**
     * Converts dates and times to string for printing.
     *
     * @return dates and times
     */
    public String getDateAndTimeForPrint() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Converts date and times to string to save.
     *
     * @return dates and times.
     */
    public String getDateAndTimeForSave() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }


    /**
     * Returns the information of the deadline for printing.
     *
     * @return Deadline in string format.
     */
    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[D]" +"[" + statusIcon + "] " + description +  " (at: "
                + getDateAndTimeForPrint()+ ")";
    }
    /**
     * Sets the complete status of the deadline.
     *
     * @param isDone Complete status to be updated to.
     */
    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }


    /**
 * Returns the information of the deadline for storing.
 *
 * @return Deadline in string format.
 */
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", isDoneInNumber, description, getDateAndTimeForSave()) + System.lineSeparator();
    }
}
