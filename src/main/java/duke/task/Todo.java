package duke.task;

public class Todo extends Task {
    /**
     * Creates a new todo object
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
