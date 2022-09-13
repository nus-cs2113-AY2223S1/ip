package duke.task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void echo() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + getTaskInfo());
    }
    public String getTaskInfo() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
