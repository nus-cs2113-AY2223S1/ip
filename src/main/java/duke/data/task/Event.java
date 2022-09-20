package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";

    public Event(String description, String date) {
        super(description);
        this.date.setData(date);
    }

    public Event(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (this.wrapType(TYPE_EVENT) + this.getStatusIcon() + " " + this.description + "\t(" + printdate("d MMM yyyy")
                + ")");
    }
}
