public class Todo extends Task implements FormatChecker {

    private static final String TODO_MARK = "[T]";
    private static final TaskType taskType = TaskType.TODO;

    /**
     * Default constructor for a Todo instance
     *
     * @param description description of Todo
     */
    public Todo(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * get the icon that indicates whether the event is marked
     *
     * @return string of icon for mark status
     */
    @Override
    public String getStatusIcon() {
        return TODO_MARK + super.getStatusIcon();
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
