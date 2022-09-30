package duke.task;

/**
 * This class helps to store the information of a task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, representing whether it is done or not
     * @return the status icon, of String data type
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    public String getTaskIcon() {
        return "T";
    }

    public String getDescription() {
        return this.description;
    }

    public String getRawDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void unMarkTask() {
        this.isDone = false;
    }

}
