package Duke.task;

/**
 * Todo tasks are basic tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Create a new Todo task
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Format the task as a String to display as output
     * @return Formatted String of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}