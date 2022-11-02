package duke.data.task;

/**
 * Represent parent class Task
 */
public abstract class Task {
    public Description description;
    public String taskType;
    public Date date = new Date();
    public boolean isDone;

    public Task(String description) {
        this.description = new Description(description);
        this.setIsDone(false);
    }

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public String getType() {
        return this.taskType;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[X]" : "[ ]");
    }

    /**
     * toString for viewing
     */
    public String toString() {
        return (this.wrapType(taskType) + this.getStatusIcon() + " " + this.description.getData());
    }

    /**
     * Wrap task type in bracket
     */
    public String wrapType(String type) {
        return "[" + type + "]";
    }

}
