package duke.tasks;

/**
 * Represent a task with description and status specified.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "[T]";
    }
}
