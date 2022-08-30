public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        String statusIcon = "[T]";
        return statusIcon + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
