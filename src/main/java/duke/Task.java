package duke;

/**
 * The Task class which provides the generic methods and variables of a task
 * to be further specified by the subclasses.
 */
public class Task {
    protected String task;
    protected boolean isDone;


    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Changes the status of the task to be done, so that users can tell that
     * they have finished the task when viewing the list of tasks.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of the task to be not done, so that users can tell that
     * they have not finished the task when viewing the list of tasks.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a "X" if the task is done when the tasks are listed out, as a graphical
     * representation for users to see that the task is done.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted String to be printed and displayed to the user.
     *
     * @return String formatted to display the task with its status,
     * whether it is finished or not finished.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + task;
    }

    /**
     * Returns a formatted String to be stored into the data file.
     * The true and false value of whether the task is done
     * is converted into "1" and "0" respectively for ease of storage.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        return isDoneValue;
    }
}
