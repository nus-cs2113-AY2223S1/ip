package task;

public class Event extends Task{
    private String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + at + ")");
    }
    @Override
    public String getTaskClass() {
        return "E";
    }
    @Override
    public String getDetails() {
        return at;
    }
}
