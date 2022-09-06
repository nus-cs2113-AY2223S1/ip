public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("  " + this);
    }


    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}