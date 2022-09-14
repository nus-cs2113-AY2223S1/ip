public class Task {
    protected String description;
    protected boolean isDone;
    //protected static int TIME_IDENTIFIER_LENGTH = 5; //length of separator from input, eg: /at:, /by:
    private static final String CHECK_MARK = "[X]";
    private static final String UNCHECK_MARK = "[]";
    protected static final String DEADLINE_IDENTIFIER = "/by: ";
    protected static final String EVENT_IDENTIFIER = "/at: ";
    protected static final String taskType = "Task";
    protected String time;
    /**
     * Default constructor for Task instance
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Empty constructor for Task instance
     */
    public Task() {
    }

    /**
     * Retrieve the status icon of Task
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? CHECK_MARK : UNCHECK_MARK);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
