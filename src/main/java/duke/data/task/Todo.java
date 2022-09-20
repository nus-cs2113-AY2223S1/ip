package duke.data.task;

public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails();
    }
}
