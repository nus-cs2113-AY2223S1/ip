package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String SaveAsString() {
        return "[T]" + super.SaveAsString();
    }
}
