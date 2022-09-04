package duke.task;

public class Todo extends Task {
    private static final String TYPE_TODO = "[T]";

    public Todo(String description) {

        super(description);
        this.taskType = TYPE_TODO;
    }
}
