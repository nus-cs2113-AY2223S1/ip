package duke.data.task;

import static duke.common.Constants.TIME_OUTPUT_PATTERN;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * <code>Event</code> represents a task to be completed by a user at a specific date and time.
 */
public class Event extends Task {
    // The date and time when the task should be done.
    private LocalDateTime eventTime;

    /**
     * Constructor of <code>Event</code>. Stores the description and date time of the task.
     *
     * @param taskName  Description of the task.
     * @param eventTime Time when the task should be completed.
     */
    public Event(String taskName, LocalDateTime eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * Return the time when the event should be completed.
     *
     * @return A date time value of the task.
     */
    public String getEventTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_OUTPUT_PATTERN);
        return eventTime.format(formatter);
    }

    /**
     * Return only the date of the time when the event should be completed.
     *
     * @return A date value of the task.
     */
    public LocalDate getEventDate() {
        LocalDate localDate = eventTime.toLocalDate();
        return localDate;
    }

    /**
     * Return the formatted event task details with task name, task completion status icon and date time.
     *
     * @return A string containing the formatted event task details.
     */
    @Override
    public String getTaskFullDetails() {
        return String.format("[E]%s (at: %s)",
                super.getTaskFullDetails(), this.getEventTime());
    }

    /**
     * Return the formatted event task details for storing.
     *
     * @return A string containing the formatted event task details.
     */
    @Override
    public String getTaskInStorageFormat() {
        String format = String.format("%s | %s | %s", "E", super.getTaskInStorageFormat(), this.getEventTime());
        return format;
    }
}
