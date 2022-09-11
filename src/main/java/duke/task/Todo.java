package duke.task;

public class Todo extends Task {
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String getTaskDetails() {
        return "[T]" + super.getTaskDetails();
    }
}
