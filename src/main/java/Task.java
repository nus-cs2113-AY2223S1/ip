
public class Task {
    private String task;
    private boolean isDone;


    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void markasDone() {
        this.isDone = true;
    }

    public void Unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
