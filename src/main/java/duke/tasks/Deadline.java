package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime by;
    public static final String TASK_TYPE = "D";

    public String getDeadline(String format) {
        return Parser.getFormattedTime(by, format);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + getDeadline(Parser.PRINT_TIME_FORMAT) + ")";
    }

    public String getTaskType() {
        return TASK_TYPE;
    }
}
