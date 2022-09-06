public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("  " + this);
    }


    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";

    }
}