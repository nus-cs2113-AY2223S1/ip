package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private final LocalDateTime dateAndTime;
    public Event(String description, String dateAndTime) {
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
        return "[E]" +"[" + statusIcon + "] " + description +  " (at: "
                + getDateAndTimeForPrint()+ ")";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("E | %d | %s | %s", isDoneInNumber, description, getDateAndTimeForSave()) + System.lineSeparator();
    }
}
