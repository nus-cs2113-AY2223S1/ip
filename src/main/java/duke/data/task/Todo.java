package duke.data.task;

/**
 * Provides the management of a todo task with getters and setters.
 */
public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";
    /**
     * Instantiates a new todo task when user initialises a new instance of this class.
     *
     * @param title A string that represents the title of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Gets the completion status and title of a todo task.
     *
     * @return A string containing the information for a todo task.
     */
    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails();
    }
}
