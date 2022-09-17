package duke.task;

/**
 * A representation of Todo.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description The todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the todo task information to store in hard-drive.
     *
     * @return A formatted string of todo task information.
     */
    @Override
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return "T" + STORE_DIVIDER + (isDone() ? "1" : "0") + STORE_DIVIDER + getDescription() + "\n";
    }

    /**
     * Overrides toString method of Task to get string representation of Todo task.
     *
     * @return A string representation of Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
