package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represent a todo task with its description.
 * A <code>Todo</code> object corresponds to a todo task represented by its description and status of whether it is done
 * or not.
 */
public class Todo extends Task {
    public static final String ICON = "T";

    /**
     * Constructor of <code>Todo</code> class with the description specified.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of <code>Todo</code> class with description and task status specified.
     * @param description Description of the todo task.
     * @param status Status of the todo task in whether it is done or not.
     *                   The status is either <code>Task.DONE_VALUE</code> or <code>Task.UNDONE_VALUE</code>.
     */
    public Todo(String description, String status) {
        super(description, status);
    }

    /**
     * Return the formatted string a <code>Todo</code> object is represented by.
     * @return The formatted string a <code>Todo</code> object is displayed.
     */
    @Override
    public String toString() {
        return ("[" + ICON + "]" + super.toString());
    }

    /**
     * Return the string that represent the <code>Todo</code> object in the data file.
     * The string is corresponding to a line in the file.
     * @return The formatted string that the <code>Todo</code> object is represented in the data file.
     */
    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description);
    }
}
