public class EventTask extends Task {
    private String eventDateTime;

    public EventTask(String name, String eventDateTime) throws DukeException {
        super(name);
        if ("".equals(name)) {
            throw new DukeException("☹ OOPS!!! Event name cannot be empty");
        }
        if (eventDateTime == null) {
            throw new DukeException("☹ OOPS!!! Please provide a date and time (/at)");
        }
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateTime + ")";
    }

}
