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
    protected boolean isDone = false;


    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public boolean getIsDone() {
        return isDone;
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

    public String getDateTime() {
        return "";
    }




    @Override
    public String toString() {
        return "["+this.taskType+ "]"+ "["+ this.getStatusIcon()+ "] " +description;
    }
}