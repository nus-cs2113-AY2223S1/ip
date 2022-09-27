package duke.task;

public class Task {
    String description;
    /**
     * A boolean value identifying whether the task is completed
     */
    boolean isDone;

    /**
     * Initializes a Task class
     * @param description What the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return an X character if the isDone value is true, return a space character if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * @return the description value of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the isDone value of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * sets the isDone value to true
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * sets the isDone value to false
     */
    public void setNotDone() {
        isDone = false;
    }

    /**
     * Changes the description value of a task to a new description
     * @param description What the task is about
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Concatenates the isDone and description value and square brackets
     * @return a String with the isDone value enclosed by two square brackets followed by its description
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Similar to toString in the Task class however is overrided in a different way in its children classes
     * @return a String with the isDone value enclosed by two square brackets followed by its description
     */
    public String toSaveString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * @return type of the Task
     */
    public String getType(){
        return "";
    }
}