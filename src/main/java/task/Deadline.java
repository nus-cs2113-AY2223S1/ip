package task;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + toString());
    }
    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + by + ")");
    }
}
