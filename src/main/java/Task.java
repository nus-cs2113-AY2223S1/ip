public class Task {
    protected String description;
    protected boolean isDone;
    protected int id;
    protected static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks ++;
        this.id = numberOfTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void printTask() {
        System.out.println(this.id + ".[" + this.getStatusIcon() + "] " + this.description);
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
    //...
}