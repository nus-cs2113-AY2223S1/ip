package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String formattedInformation() {
        String divider = " | ";
        String information = "T" + divider + (isDone ? "1" : "0") + divider + this.description;
        return information;
    }
}