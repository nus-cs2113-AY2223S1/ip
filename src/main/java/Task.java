public class Task {
    protected String description;
    protected boolean isDone;
    protected static int SEPARATOR_LENGTH = 5; //length of separator from input, eg: /at:, /by:

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
