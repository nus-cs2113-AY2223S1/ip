public class Event extends Task {
    protected String at;

    public Event(String descriptionString, String at) {
        super(descriptionString);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.descriptionString + " (at:" + this.at + ")";
    }
}
