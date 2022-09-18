public class ToDo extends Task {
    private String taskType;
    public ToDo(String name) {
        super(name);
        this.taskType = "[T]";
    }

    public String toString() {
        return taskType + getCheckBox() + " "+ getName();
    }

    public String getFileInput() {
        return "T" + " | " + String.valueOf(this.getIsCompleted()) + " | " + this.getName();
    }
}
