package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("T | %d | %s", isDoneInNumber, description) + System.lineSeparator();
    }
}

