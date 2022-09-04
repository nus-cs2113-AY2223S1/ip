package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskDetails() {
        return "[T]" + super.getTaskDetails();
    }
}
