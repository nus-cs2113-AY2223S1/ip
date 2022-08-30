public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "Added new event : [E] " + super.toString() + " (at: " + at + ")";
    }

    public String printList() {
        return "[E] [" + super.getStatusIcon() + "] " + super.description + " (at: " + at + ")";
    }

}
