package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represent a deadline with its description, and the date and time of the deadline.
 * A <code>Deadline</code> object corresponds to a deadline represented by its description, deadline date and time, and
 * status of whether it is done or not.
 */
public class Deadline extends Task {
    private final String deadlineDate;

    public static final String ICON = "D";
    public static final String SEPARATOR = " /by ";

    /**
     * Constructor of <code>Deadline</code> class with specified description and deadline date.
     * @param description Description of what the deadline is for.
     * @param deadlineDate Date and time of the deadline task.
     */
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructor of <code>Deadline</code> class with specified description, deadline date and status specified.
     * @param description Description of what the deadline is for.
     * @param deadlineDate Date and time of the deadline task.
     * @param status Status of the deadline in whether it is done or not.
     */
    public Deadline(String description, String deadlineDate, String status) {
        super(description, status);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Return the formatted string a <code>Deadline</code> object is represented by.
     * @return The formatted string a <code>Deadline</code> object is displayed.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ICON, super.toString(), this.deadlineDate);
    }

    /**
     * Return the string that represent the <code>Deadline</code> object in the data file.
     * The string is corresponding to a line in the file.
     * @return The formatted string that the <code>Deadline</code> object is represented in the data file.
     */
    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description, this.deadlineDate);
    }
}
