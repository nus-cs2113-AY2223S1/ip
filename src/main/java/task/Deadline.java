package task;

public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + by + ")");
    }
    @Override
    public String getTaskClass() {
        return "D";
    }
    @Override
    public String getDetails() {
        return by;
    }
}
