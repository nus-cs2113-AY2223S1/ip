package Duke;

public class Task {
    private boolean isDone;
    private String taskDescription;

    private final String STATUS_DONE_ICON = "X";
    private final String STATUS_NOTDONE_ICON = " ";

    public Task() {
        this("");
    }

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.taskDescription;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getStatusIcon() {

        if (this.isDone) {
            return STATUS_DONE_ICON;
        } else {
            return STATUS_NOTDONE_ICON;
        }

    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }
}


