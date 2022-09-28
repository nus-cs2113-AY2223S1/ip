package duke.task;

/**
 * Represents a todo-type task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Contains full description of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
