public class Event extends Task{

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + toString());
    }

    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + by + ")");
    }
}
