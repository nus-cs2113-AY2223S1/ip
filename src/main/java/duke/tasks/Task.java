package duke.tasks;

public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatus() {
        if (isCompleted) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getType() {
        return "[ ]";
    }

    public String getAddedInfo() {
        return "";
    }

    public void setCompletion(boolean isComplete) {
        this.isCompleted = isComplete;
    }

    public String getDescription() {
        return description;
    }
}
