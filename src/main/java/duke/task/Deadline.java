package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private final LocalDateTime dateAndTime;
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
    public String getDateAndTimeForPrint() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
    public String getDateAndTimeForSave() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[D]" +"[" + statusIcon + "] " + description +  " (at: "
                + getDateAndTimeForPrint()+ ")";
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", isDoneInNumber, description, getDateAndTimeForSave()) + System.lineSeparator();
    }
}
