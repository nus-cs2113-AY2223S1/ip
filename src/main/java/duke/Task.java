package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String category;


    /**
     * Creates duke.Task Object
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.category = "Task";
    }

    /**
     * Returns the status of the task
     * @return status as "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    /**
     * Sets Status to according to mark
     */
    public void setStatus(Boolean isMark) {
        isDone = isMark;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Returns the Description of the task
     * @return description
     */
    public String getDescription() {
        return (description);
    }

    public String getSavedString() {
        return "  | " + getStatusIcon() + " | " + description;
    }


    @Override
    public String toString() {
        return "[ ]" + "[" + getStatusIcon() +"] " + description;
    }

}