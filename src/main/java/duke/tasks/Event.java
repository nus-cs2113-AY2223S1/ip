package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime at;
    public static final String TASK_TYPE = "E";

    public String getTime(String format) {
        return Parser.getFormattedTime(at, format);
    }


    public Event(String description, String at) {
        super(description);
        this.at = Parser.parseDateTime(at);
    }

    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (at: " + getTime(Parser.PRINT_TIME_FORMAT) + ")";
    }

    public String getTaskType() {
        return TASK_TYPE;
    }
}
