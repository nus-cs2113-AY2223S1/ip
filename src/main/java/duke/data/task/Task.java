package duke.data.task;

/**
 * Provides the management of a task with getters and setters.
 */
public class Task {
    private String title;
    private Boolean isDone;
    private static final String DONE = "X";
    private static final String NOT_DONE= " ";
    private static final String STATUS_ICON_PREFIX = "[";
    private static final String STATUS_ICON_POSTFIX = "] ";

    /**
     * Instantiates a new task when user initialises a new instance of this class.
     *
     * @param title A string that represents the title of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Gets the deadline of a deadline task.
     *
     * @return A string that represents the deadline of the task.
     */
    public String getDueBy() {
        return getDueBy();
    }

    /**
     * Gets the time of an event task.
     *
     * @return A string that represents the time that the task will be held at.
     */
    public String getTime() {
        return getTime();
    }

    /**
     * Gets the title of a task.
     *
     * @return A string that represents the title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the task completion status of a task as an icon in string format.
     *
     * @return A string that represents the task completion status as an icon.
     */
    public String getStatusIcon() {
        return (isDone ? DONE : NOT_DONE);
    }

    /**
     * Gets the completion status and title of a task.
     *
     * @return A string containing the information for a task.
     */
    public String getTaskDetails() {
        return STATUS_ICON_PREFIX + getStatusIcon() + STATUS_ICON_POSTFIX + title;
    }

    /**
     * Marks a task as done or undone.
     *
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }
}
