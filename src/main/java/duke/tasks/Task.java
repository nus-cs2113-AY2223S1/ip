package duke.tasks;

public class Task {
    protected String description;
    protected boolean isTaskCompleted;

    public Task(String description) {
        this.description = description;
        this.isTaskCompleted = false;
    }

    public String getStatus() {
        if (isTaskCompleted) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTaskType() {
        return "[ ]";
    }

    public String getAddedInfo() {
        return "";
    }

    public void setCompletion(boolean isComplete) {
        this.isTaskCompleted = isComplete;
    }

    public String getDescription() {
        return description;
    }
}
