package duke.task;


/**
 * task class which is the base class for deadline event and todo to inherit
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * gets the description of the task
     *
     * @return description of task as String
     */
    public String getDescription() {
        return description;
    }

    /**
     * get the status icon of the task
     *
     * @return status icon of task as String
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * mark task as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * mark task as undone
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * returns task description with status icon as String
     *
     * @return task with info as String
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * converts task to String to write into file
     *
     * @return String with task info formatted
     * @throws NullPointerException
     */
    public String taskToString() throws NullPointerException {
        if (isDone) {
            return "|" + "1" + "|" + description;
        }
        return "|" + "0" + "|" + description;
    }
}
