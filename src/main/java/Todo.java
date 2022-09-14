public class Todo extends Task implements FormatChecker {

    private static final String TODO_MARK = "[T]";
    private static final String taskType = "T";

    /**
     * Default constructor for a Todo instance
     *
     * @param description description of Todo
     */
    public Todo(String description) throws WrongCommandFormatException {
        super(description);
        FormatChecker.checkNullString(description);
    }

    public Todo(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String getStatusIcon() {
        return TODO_MARK + super.getStatusIcon();
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
