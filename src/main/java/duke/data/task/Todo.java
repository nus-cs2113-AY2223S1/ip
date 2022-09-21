package duke.data.task;

/**
 * Represent Todo Task
 */
public class Todo extends Task {

    public static final String TYPE_TODO = "T";

    public Todo(String description) {
        super(description);
        this.taskType = TYPE_TODO;
    }

    /**
     * Initiate with isDone
     */
    public Todo(Boolean isDone, String description) {
        this(description);
        this.setIsDone(isDone);
    }
}
