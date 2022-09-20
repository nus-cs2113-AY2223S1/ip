package duke.data.task;

public abstract class Task {
    public Description description;
    public String taskType;
    public Date date;
    public boolean isDone;

    public Task(String description) {
        this.description.setData(description);
        this.setIsDone(false);
    }

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public String getType(){
        return this.taskType;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (this.getIsDone() ? "[X]" : "[ ]");
    }

    public String toString() {
        return (this.wrapType(taskType) + this.getStatusIcon() + " " + this.description);
    }

    public String wrapType(String type) {
        return "[" + type + "]";
    }

}
