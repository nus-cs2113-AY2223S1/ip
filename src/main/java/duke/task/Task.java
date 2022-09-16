package duke.task;

public abstract class Task {
    private String name;
    private boolean status;

    public Task() {
        this("");
    }

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone() ? "X" : " ", this.getName());
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return status;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }
}