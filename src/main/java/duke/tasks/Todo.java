package duke.tasks;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String showTask() {
        return "[T][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription;
    }
}