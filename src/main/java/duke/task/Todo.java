package duke.task;

/**
 * A clas representing a todo.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description Description of this todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Fixes how this Todo would look like if it were to be converted into a string
     * based on its description.
     *
     * @return The string representation of this Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }
}
