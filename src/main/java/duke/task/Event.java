package duke.task;

public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration.replace("at ", "");
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[E]" + str + " (at: " + duration + ")";
    }
}
