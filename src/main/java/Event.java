public class Event extends Task{
    protected String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (at: " + this.time + ")";
    }
}
