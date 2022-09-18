package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";
    public static final String TYPE_EVENT_WRAP = "[E]";
    public String dateString;
    public LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.taskTypeWrap = TYPE_EVENT_WRAP;
        this.taskType = TYPE_EVENT;
        try{
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e){
            this.date = null;
            this.dateString = date;
        }  
    }

    public Event(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description + "(" + printdate() + ")");
    }

    @Override
    public String toSave() {
        return (this.taskType + LIMITER + this.isDone + LIMITER + this.description + LIMITER + printdate() + "\n");
    }
    @Override
    public boolean isDateNull(){
        return (this.date == null)? true : false;
    }
    
    private String printdate() {
        return (this.date == null) ? this.dateString : this.date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
    }
}
