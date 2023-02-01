package duke.data.task;

/**
 * <code>Todo</code> represents a basic task to be completed by a user.
 */
public class Todo extends Task {
    /**
     * Constructor of <code>Todo</code>. Stores the description of the task.
     *
     * @param taskName Description of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the formatted to-do task details with task name and task completion status icon.
     *
     * @return A string containing the formatted to-do task details.
     */
    @Override
    public String getTaskFullDetails() {
        return String.format("[T]%s", super.getTaskFullDetails());
    }

    /**
     * Returns the formatted to-do task details for storing.
     *
     * @return A string containing the formatted to-do task details.
     */
    @Override
    public String getTaskInStorageFormat() {
        String format = String.format("%s | %s", "T", super.getTaskInStorageFormat());
        return format;
    }
}
