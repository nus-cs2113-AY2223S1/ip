package duke.task;

public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    @Override
    public String getTaskDetails() {
        return "[T]" + super.getTaskDetails();
    }
}
