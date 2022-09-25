package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Child of parent class Task
 * Contains description which is the name of event, date and time which is the
 * date and time due of the event respectively
 */
public class Event extends Task {
    private String time;
    private LocalDate date;
    /**
     * Method creates new event object
     * @param description is the name of the event
     * @param at is the date + time of the event as a string
     * Method reads in string at, which contains date and time
     * Method splits date and time, and converts date from string to LocalDate
     * Date can only be read in "yyyy-MM-dd"
     * Time will be kept as string
     */
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
    /**
     * @return full details of deadline including:
     * type as "[E]"
     * status of mark/unmark, [X] if marked, [ ] if unmarked
     * description which is name of event
     * date of event in the format "MMM d yyyy"
     * time of event
     */
    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + time + ")");
    }
    /**
     * @return type of child, which is "E"
     */
    @Override
    public String getTaskClass() {
        return "E";
    }
    /**
     * @return date and time of event task as a full string
     */
    @Override
    public String getDetails() {
        return (date.toString() + time);
    }
}
