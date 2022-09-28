package duke.task;

/**
 * Represents a todo in the Duke system.
 * A todo object is abbreviated as 'O'.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns formatted string of this todo.
     * @return todo string.
     */
    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[O]" + str;
    }
}
