public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskDetails() {
        return "[T]" + super.getTaskDetails();
    }
}
