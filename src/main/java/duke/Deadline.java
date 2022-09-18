package duke;
public class Deadline extends Task {
    private String dueDate;
    private String taskType;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.taskType = "[D]";
    }

    public String toString() {
        return  taskType + getCheckBox() + " "+ getName() + " (by: " + dueDate + ")";
    }
    public String getFileInput() {
        return "D" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName() + " | " + dueDate;
    }
}