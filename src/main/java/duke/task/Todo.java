package duke.task;

/**
 * <code>Todo</code> is a task.
 */
public class Todo extends Task {

    /**
     * Constructor of the <code>Todo</code> object.
     * Stores the description of the task.
     *
     * @param description
     */
    public Todo(String description) {
        super("todo", description);
    }

    /**
     * Returns the formatted string containing task type and completion status.
     *
     * @return string containing task type and completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted string containing task type, completion status indicated by 1 or 0 and
     * task description of the task.
     *
     * @return the formatted string with task details
     */
    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }
}