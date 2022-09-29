package duke.task;

public class Todo extends Task {
    /**
     * Represents a new Todo. A <code>Todo</code> object corresponds to a description of the todo
     * task.
     * 
     * @param description the description of the todo object
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats how to print the todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
