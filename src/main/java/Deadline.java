public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "Added deadline: [D] " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String printList() {
        return "[D] [" + super.getStatusIcon() + "] " + super.description + " (by: " + by + ")";
    }
}