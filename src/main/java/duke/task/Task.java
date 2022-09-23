package duke.task;

/**
 * Abstract task class.
 */
public abstract class Task {
    public static String DONE_PARAM = "done";
    private String name;
    private boolean status;

    /**
     * Initializes a blank task.
     */
    public Task() {
        this("");
    }

    /**
     * Initializes a task with a name.
     * 
     * @param name Name
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Initializes a task with a name and doneness status.
     * 
     * @param name   Name
     * @param status True if task is done and false otherwise
     */
    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Formats a task as a string to be saved to a data file. The serialized task
     * should be unserialized by the TaskFactory.
     * 
     * @return Serialized task
     */
    public String serialize() {
        return getKeyword() + " " + getName() + " /" + DONE_PARAM + " " + isDone();
    }

    public abstract String getKeyword();

    /**
     * Formats a task as a string to be displayed to the user.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone() ? "X" : " ", this.getName());
    }

    /**
     * Gets the name of the task.
     * 
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets whether task is done.
     * 
     * @return True if the task is done and false otherwise
     */
    public boolean isDone() {
        return status;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.status = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.status = false;
    }
}