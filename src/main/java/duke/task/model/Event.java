package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represent an event with its description, date and time.
 * An <code>Event</code> object corresponds to an event represented by its description, date and time, and its status
 * of whether it is done or not.
 */
public class Event extends Task {
    private final String datetime;

    public static final String ICON = "E";
    public static final String SEPARATOR = " /at ";

    /**
     * Constructor of the <code>Event</code> class with specified description and datetime of the event.
     * @param description Description of the event.
     * @param datetime The date and time of the event.
     */
    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Constructor of <code>Event</code> class with description and task status specified.
     * @param description Description of the event.
     * @param status Status of the event in whether it is done or not.
     *                   The status is either <code>Task.DONE_VALUE</code> or <code>Task.UNDONE_VALUE</code>.
     */
    public Event(String description, String datetime, String status) {
        super(description, status);
        this.datetime = datetime;
    }

    /**
     * Return the formatted string a <code>Event</code> object is represented by.
     * @return The formatted string a <code>Event</code> object is displayed.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", ICON, super.toString(), this.datetime);
    }

    /**
     * Return the string that represent the <code>Event</code> object in the data file.
     * The string is corresponding to a line in the file.
     * @return The formatted string that the <code>Event</code> object is represented in the data file.
     */
    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description, this.datetime);
    }
}
