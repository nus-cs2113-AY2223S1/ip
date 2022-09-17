package duke.task;

public class Todo extends Task {

    public static final String TYPE_TODO = "T";
    public static final String TYPE_TODO_WRAP = "[T]";

    public Todo(String description) {
        super(description);
        this.taskTypeWrap = TYPE_TODO_WRAP;
        this.taskType = TYPE_TODO;
    }
}
