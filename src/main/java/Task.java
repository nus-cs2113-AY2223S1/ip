public class Task {
    protected String description;
    protected boolean isDone;
    protected static int TIME_SEPARATOR_LENGTH = 5; //length of separator from input, eg: /at:, /by:
    private static final String CHECK_MARK = "[X]";
    private static final String UNCHECK_MARK = "[]";
    protected static final String TIME_SEPARATOR = "/";

    /**
     * Deafult constructor for Task instance
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
