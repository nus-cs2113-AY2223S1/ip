public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskDetails() {
        return "[E]" + super.getTaskDetails() + " (at: " + startTime + "-" + endTime + ")";
    }
}
