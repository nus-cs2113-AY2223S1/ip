package duke.tasks;

public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    public String getTaskType() {
        return TASK_TYPE;
    }
}
