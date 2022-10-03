package duke;
/**
 * Deadline is a subclass of Task. The Deadline object inherits the attribute name from the superclass which
 * represents the deadline description. Deadline is denoted by task type "[D]" and requires a due date attribute in
 * String format for Deadline to be valid.
 */
public class Deadline extends Task {
    private String dueDate;
    private String taskType;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.taskType = "[D]";
    }
    /**
     * Returns type of task, checkbox, name of task and due date in String format.
     *
     * @return Deadline in String format.
     */
    public String toString() {
        return  taskType + getCheckBox() + " "+ getName() + " (by: " + dueDate + ")";
    }

    /**
     * Returns Deadline attributes in String format separated by delimiter "|"
     *
     * @return Deadline in String format for storage in local file.
     */
    public String getFileInput() {
        return "D" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName() + " | " + dueDate;
    }
}