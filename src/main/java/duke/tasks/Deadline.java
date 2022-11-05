package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

/**
 * This class is a specific type of {@code Task}s that have a deadline.
 *
 * @author Dhanish
 */
public class Deadline extends Task {

    private LocalDateTime by;
    public static final String TASK_TYPE = "D";

    /**
     * @return the time this {@code Deadline} is due by.
     */
    public String getDeadline(String format) {
        return Parser.getFormattedTime(by, format);
    }

    /**
     * Constructor that initialises the {@code Deadline} based on its description and when it is due.
     *
     * @param description - a literal that describes the {@code Deadline}
     * @param by          - the time by which this {@code Deadline} is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    /**
     * @return - a textual representation of this {@code Deadline}
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + getDeadline(Parser.PRINT_TIME_FORMAT) + ")";
    }

    /**
     * @return a {@code String} constant that denotes the type of {@code Task} this {@code Deadline} is.
     */
    public String getTaskType() {
        return TASK_TYPE;
    }
}
