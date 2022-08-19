
public class Task {
    private String name;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n",
                this.isDone() ? "X" : " ",
                this.getName());
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