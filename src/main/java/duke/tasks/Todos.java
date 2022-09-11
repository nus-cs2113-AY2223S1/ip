package duke.tasks;

public class Todos extends Task {
    public Todos(String taskName) {
        super(taskName);
        this.type = "T";
    }

    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[T]" + done + this.getTaskName();
    }
}
