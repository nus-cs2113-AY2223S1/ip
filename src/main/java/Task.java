/**
 * Tasks to be added with attribute to track completion status
 * */

public abstract class Task {
    protected final String taskDescription;
    protected boolean isDone;
    // constructor
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    // getters
    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markDone(){
        isDone = true;
    }

    /**
     * Abstract that serves as base for formatting taskDescription
     * @return String describing task
     * */
    public abstract String showTask();

}
