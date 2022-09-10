package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toOutputFileFormat() {
        String out = "event " + description + " /at " + at;
        if (this.isDone) {
            return (out + " X");
        } else {
            return (out + " O");
        }
    }
}