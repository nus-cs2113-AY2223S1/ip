package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Child of parent class Task
 * Contains description which is the name of deadline, date and time which is the
 * date and time due of the deadline respectively
 */
public class Deadline extends Task {
    private String time;
    private LocalDate date;
    /**
     * Method creates new deadline object
     * @param description is the name of the deadline
     * @param by is the date + time of the deadline as a string
     * Method reads in string by, which contains date and time
     * Method splits date and time, and converts date from string to LocalDate
     * Date can only be read in "yyyy-MM-dd"
     * Time will be kept as string
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] DateTime = by.split(" ");
        if (DateTime.length == 2) {
            this.date = LocalDate.parse(DateTime[0], formatter);
            this.time = " " + DateTime[1];
        } else {
            this.date = LocalDate.parse(by, formatter);
            this.time = "";
        }
    }
    /**
     * @return full details of deadline including:
     * type as "[D]"
     * status of mark/unmark, [X] if marked, [ ] if unmarked
     * description which is name of deadline
     * date of deadline in the format "MMM d yyyy"
     * time of deadline
     */
    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description
                    + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + time + ")");
    }
    /**
     * @return type of child, which is "D"
     */
    @Override
    public String getTaskClass() {
        return "D";
    }
    /**
     * @return date and time of deadline task as a full string
     */
    @Override
    public String getDetails() {
        return (date.toString() + time);
    }
}
