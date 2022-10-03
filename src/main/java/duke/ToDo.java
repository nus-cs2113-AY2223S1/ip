package duke;
/**
 * ToDo is a subclass of Task. The ToDo object inherits the attribute, name from the superclass which represents
 * description for the ToDo task. ToDo is denoted by task type "[T]"
 */
public class ToDo extends Task {
    private String taskType;
    public ToDo(String name) {
        super(name);
        this.taskType = "[T]";
    }

    /**
     * Returns type of task, checkbox and name of task in String format.
     *
     * @return ToDo in String format.
     */
    public String toString() {
        return taskType + getCheckBox() + " "+ getName();
    }

    /**
     * Returns ToDo attributes in String format separated by delimiter "|"
     *
     * @return ToDo in String format for storage in local file.
     */
    public String getFileInput() {
        return "T" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName();
    }
}
