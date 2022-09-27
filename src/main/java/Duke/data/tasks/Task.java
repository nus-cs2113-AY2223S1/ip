package Duke.data.tasks;

import java.time.LocalDate;

/**
 * Superclass of all task classes
 */
public abstract class Task {
    protected String description;
    protected String dueDate;
    protected String taskType;
    protected boolean isDone;
    public LocalDate assignedDate = LocalDate.parse("1999-01-01");

    /**
     * Constructor for the Task class.
     * Sets initial task as undone.
     */
    public Task() {
        this.isDone = false;
    }

    /**
     * This method returns the protected description variable
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method checks the completion status of task and
     * returns a symbol corresponding to the status
     * @return symbols representing status of task completion
     */
    public String getStatusIcon() {
        return (this.isDone) ? "X" : " ";
    }

    /**
     * This method formats the protected variable tasktype by adding square brackets and
     * returns it as a string
     * @return string showing formatted type of task
     */
    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    /**
     * This method formats the completion status icon by adding square brackets and
     * returns it as a string
     * @return string showing formatted completion status
     */
    public String getMarkStatus() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Setter for local variable isDone
     * @param isMark
     */
    public void setDone(boolean isMark) {
        isDone = isMark;
    }

    /**
     * This method formats the type of task, completion status icon, description and due date of
     * the task into a presentable form and prints ot.
     */
    public void printTask() {
        System.out.println(this.getTaskType() + this.getMarkStatus() + " " +
                this.description + "\t\t" + this.dueDate);
    }

    /**
     * This method converts some local variable into a single String for storing purposes
     * @return a string containing local variables
     */
    @Override
    public String toString() {
        return this.getMarkStatus() + " " + this.taskType + " " +
                this.description + " " + this.dueDate;
    }

}
