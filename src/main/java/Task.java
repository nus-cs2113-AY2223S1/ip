public class Task {
    protected String description;
    protected boolean isDone;
    protected static int TIME_SEPARATOR_LENGTH = 5; //length of separator from input, eg: /at:, /by:
    private static final String CHECK_MARK = "[X]";
    private static final String UNCHECK_MARK = "[]";
    protected static final String TIME_SEPARATOR = "/";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
    }

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
