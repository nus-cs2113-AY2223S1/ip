package Duke.Tasks;

public class Event extends Tasks {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {

        return "[E]" + getStatusIcon() + super.description + " (at: " + this.at + ")";
    }

    @Override
    public String toFile() {
        return "E|" + ((this.isDone) ? 1 : 0) + "|" + super.description + " | " + this.at + "\n";
    }
}
