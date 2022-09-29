package duke.tasks;

/**
 * Tasks to be added to TaskList with attribute to track completion status
 *  String <code>taskDescription</code> contains the description of the task; and
 *  boolean <code>isDone</code> represents whether the task has been completed.
 */


public abstract class Task {
    protected final String taskDescription;
    protected final String DONE_SYMBOL = "X";
    protected boolean isDone;

    /** Constructors with and without isDone */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /** Getter function for <code>taskDescription</code>*/
    public String getTaskDescription() {
        return this.taskDescription;
    }
    /** Getter function for <code>isDone</code>*/
    public boolean getIsDone() {
        return this.isDone;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Abstract that serves as base for formatting taskDescription
     *
     * @return String describing task
     */
    public abstract String showTask();
    /**
     * Abstract that serves as base for formatting data written into storage file
     *
     * @return String of formatted task to be written into storage file
     */
    public abstract String writeTaskToFile();

}
