package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String time;
    private LocalDate date;
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] DateTime = at.split(" ");
        if (DateTime.length == 2) {
            this.date = LocalDate.parse(DateTime[0],formatter);
            this.time = " " + DateTime[1];
        } else {
            this.date = LocalDate.parse(at,formatter);
            this.time = "";
        }
    }
    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + time + ")");
    }
    @Override
    public String getTaskClass() {
        return "E";
    }
    @Override
    public String getDetails() {
        return (date.toString() + time);
    }
}
