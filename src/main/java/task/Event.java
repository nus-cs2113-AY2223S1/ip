package task;

import exception.DateParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * @throws DateParseException if date is given in the wrong format
     */
    public Event(String description, String at) throws DateParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] DateTime = at.split(" ", 2);
        try {
            this.date = LocalDate.parse(DateTime[0], formatter);
        } catch (DateTimeParseException e) {
            throw new DateParseException();
        }
        if (DateTime.length == 2) {
            this.time = " " + DateTime[1];
        } else {
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
        return ("[E][" + super.getStatusIcon() + "] " + super.description
                    + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + time + ")");
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
    public String getDateAndTime() {
        return (date.toString() + time);
    }
}
