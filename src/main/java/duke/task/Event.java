package duke.task;

public class Event extends Task {
    private String dayAndTime;
    public Event(String description, String dayAndTime) {
        super(description);
        this.dayAndTime = dayAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + dayAndTime + ")";
    }
}
