package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String TYPE_DEADLINE = "D";
    public static final String TYPE_DEADLINE_WRAP = "[D]";
    public String dateString;
    public LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.taskTypeWrap = TYPE_DEADLINE_WRAP;
        this.taskType = TYPE_DEADLINE;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.date = null;
            this.dateString = date;
        }
    }

    public Deadline(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.taskTypeWrap + this.getStatusIcon() + " " + this.description + "\t(" + printdate("d MMM yyy") + ")");
    }

    @Override
    public String toSave() {
        return (this.taskType + LIMITER + this.isDone + LIMITER + this.description + LIMITER + printdate("yyyy-MM-dd") + "\n");
    }

    @Override
    public boolean isDateNull() {
        return (this.date == null) ? true : false;
    }

    public String printdate(String pattern) {
        return (this.date == null) ? this.dateString : this.date.format(DateTimeFormatter.ofPattern(pattern));
    }
    public LocalDate getDate(){
        return this.date;
    }
}
