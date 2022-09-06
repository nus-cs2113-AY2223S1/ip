package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void echo() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + getTaskInfo());
        System.out.printf("Now you have %d tasks in the list." + System.lineSeparator(), Task.getTaskCount());
    }
    public String getTaskInfo() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
