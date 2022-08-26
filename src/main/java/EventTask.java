public class EventTask extends Task {
    private String eventDateTime;

    public EventTask(String name, String eventDateTime) {
        super(name);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateTime + ")";
    }

}
