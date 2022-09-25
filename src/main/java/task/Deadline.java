package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private String time;
    private LocalDate date;
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] DateTime = by.split(" ");
        if (DateTime.length == 2) {
            this.date = LocalDate.parse(DateTime[0],formatter);
            this.time = " " + DateTime[1];
        } else {
            this.date = LocalDate.parse(by,formatter);
            this.time = "";
        }
    }
    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + time + ")");
    }
    @Override
    public String getTaskClass() {
        return "D";
    }
    @Override
    public String getDetails() {
        return (date.toString() + time);
    }
}
