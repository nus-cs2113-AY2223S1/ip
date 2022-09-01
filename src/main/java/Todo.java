public class Todo extends Task {

    private static final String TODO_MARK = "[T]";

    /**
     * Deafult constructor for a Todo instance
     *
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return TODO_MARK + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
