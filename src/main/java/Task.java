public class Task {
    private final String name;
    private boolean isDone;
    private int number;

    public Task (int number, String name) {
        this.name = name;
        this.isDone = false;
        this.number = number;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public int getNumber() {
        return number;
    }

    public String taskInfo() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
