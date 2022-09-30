package duke;

/**
 * Class representing a Task.
 */
public class Task {
    /** Description of task */
    protected String description;

    /** Indication of whether task is completed */
    protected boolean isDone;

    /** Type of task */
    protected char taskType;

    /**
     * Class constructor for Task to initialise it with description passed in.
     * Sets task to be not done initially.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public boolean isDone(){
        return this.isDone;
    }

    /**
     * Returns X if task is marked as completed or a whitespace otherwise.
     *
     * @return String indicating if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone(boolean status){
        this.isDone = status;
    }
    public char getTaskType(){
        return this.taskType;
    }

    /**
     * Prints task information properly formatted.
     */
    public void printTask(){
        System.out.print("["+getTaskType()+"]["+getStatusIcon()+"] "+description);
    }

    /**
     * Returns a formatted string containing information of the task to be saved.
     *
     * @return Formatted string
     */
    public String getSaveString(){
        return taskType+ "|" + isDone + "|" + description;
    }

}