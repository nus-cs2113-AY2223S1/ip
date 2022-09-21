package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String category;

    /**
     * Constructor of Task Object, where Task is initialized as not done
     *
     * @param description User input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.category = "Task";
    }

    /**
     * Returns the status of the task
     *
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

    /**
     * Returns the Category of the task
     *
     * @return Category attrobite
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the Description of the task
     *
     * @return description
     */
    public String getDescription() {
        return (description);
    }

    /**
     * Returns the String to be saved to Storage File
     *
     * @return saved String
     */
    public String getSavedString() {
        return "  | " + getStatusIcon() + " | " + description;
    }

    /**
     * Printed version of Task
     *
     * @return Output message of task when printed
     */
    @Override
    public String toString() {
        return "[ ]" + "[" + getStatusIcon() +"] " + description;
    }

}