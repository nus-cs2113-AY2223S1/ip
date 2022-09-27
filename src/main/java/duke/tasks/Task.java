package duke.tasks;

/**
 * This abstract class provides the template to create specialised {@code Task}s.
 *
 * @author Dhanish.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    /**
     * @return - the icon that indicates a task is done.
     */
    public static String getDoneIcon() {
        return DONE_ICON;
    }

    /**
     * @return - the icon that indicates a task is yet to be done.
     */
    public static String getNotDoneIcon() {
        return NOT_DONE_ICON;
    }

    /**
     * Constructor that initialises the {@code Task} with just a description of what the {@code Task} is
     * Sets its completion status to not done.
     *
     * @param description - describes what the task is.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * @return the icon that corresponds to the completion status of the {@code Task}.
     */
    public String getStatusIcon() {
        return isDone ? DONE_ICON : NOT_DONE_ICON;
    }

    /**
     * Marks a {@code Task} as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a {@code Task} as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * @return - the description of the {@code Task}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return - the completion status of the {@code Task}.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return - a textual representation of the details of the {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * This abstract method forces any type of Task to be able to return a symbol that corresponds to its {@code Task} type.
     *
     * @return the type of {@code Task}.
     */
    public abstract String getTaskType();

}
