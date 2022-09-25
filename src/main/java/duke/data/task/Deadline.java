package duke.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.common.Constants.TIME_OUTPUT_PATTERN;

/**
 * <code>Deadline</code> represents a task to be completed by a user before a specific date and time.
 */
public class Deadline extends Task {
    // The date and time before the task is to be done.
    private LocalDateTime deadlineTime;

    /**
     * Constructor of <code>Event</code>. Stores the description and date time of the task.
     *
     * @param taskName     Description of the task.
     * @param deadlineTime Time before the task is to be completed.
     */
    public Deadline(String taskName, LocalDateTime deadlineTime) {
        super(taskName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Return the time before the deadline is to be completed.
     *
     * @return A date time value of the task.
     */
    public String getDeadlineTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_OUTPUT_PATTERN);
        return deadlineTime.format(formatter);
    }

    /**
     * Return only the date of the time before the deadline is to be completed.
     *
     * @return A date value of the task.
     */
    public LocalDate getDeadlineDate() {
        LocalDate localDate = deadlineTime.toLocalDate();
        return localDate;
    }

    /**
     * Return the formatted deadline task details with task name, task completion status icon and date time.
     *
     * @return A string containing the formatted deadline task details.
     */
    @Override
    public String getTaskFullDetails() {
        return String.format("[D]%s (by: %s)",
                super.getTaskFullDetails(), this.getDeadlineTime());
    }

    /**
     * Return the formatted deadline task details for storing.
     *
     * @return A string containing the formatted deadline task details.
     */
    @Override
    public String getTaskInStorageFormat() {
        String format = String.format("%s | %s | %s", "D", super.getTaskInStorageFormat(), this.getDeadlineTime());

        return format;
    }
}
