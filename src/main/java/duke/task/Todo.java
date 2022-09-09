package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return "T" + STORE_DIVIDER + (isDone() ? "1" : "0") + STORE_DIVIDER + getDescription() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
