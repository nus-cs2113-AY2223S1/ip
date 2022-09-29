package duke.task;

/**
 * Represents a todo that contains a description of the task and a boolean attribute to determine whether
 * the task is done or not done.
 */
public class Todo extends Task {
    /**
     * Initializes the Todo object.
     *
     * @param description Name of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Displays the to-do message.
     *
     * @return "[T][X/ ]Task Description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
