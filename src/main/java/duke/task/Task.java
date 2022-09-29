package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String checkmark = this.isDone() ? "X" : " ";
        return String.format("[%s] %s", checkmark, description);
    }
}
