public class Task {
    private String description;
    private boolean isDone;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskCount++;
    }

    public String getDescription() {
        return this.description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }


    public void markAsDone() {
        isDone = true;
    }
    public void markAsUnDone() {
        isDone = false;
    }
}
