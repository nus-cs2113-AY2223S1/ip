package duke.task;


/**
 * to do class which inherits from Task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String taskToString() {
        return "T" + super.taskToString();
    }
}
