package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getIsDoneMarking() + "] " + getDescription();
    }

    @Override
    public String getFileFormat() {
        return "T" + super.getFileFormat();
    }

}
