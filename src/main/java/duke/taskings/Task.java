package duke.taskings;

public class Task {
    /*
     * a string storing the user's tasking.
     * */
    protected String description;
    /*
     * a boolean variable tracking the completeness of the tasking.
     */

    protected String taskType;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // mark completed task with X
        return (isDone ? "X" : " ");
    }

    public String getTaskType() {
        return (taskType);
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getBy() {
        return "";
    }

    public String getAt() {
        return "";
    }


    public String printList() {
        return "";
    }


    @Override
    public String toString() {
        return description;
    }
}