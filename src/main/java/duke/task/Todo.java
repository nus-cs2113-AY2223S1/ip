package duke.task;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskFullDetails() {
        return String.format("[T]%s", super.getTaskFullDetails());
    }
}
