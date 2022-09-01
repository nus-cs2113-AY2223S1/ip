public class Event extends Task {
    private static final String EVENT_MARK = "[E]";
    private String at;

    /**
     * Default constructor for Event instance
     *
     * @param description description of Event
     * @param at time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Event constructor for a raw string input of event
     *
     * @param complexString raw string input containing time and description of event
     */
    public Event(String complexString) {
        super();
        int separatorIndex = complexString.indexOf(TIME_SEPARATOR);
        this.description = complexString.substring(0, separatorIndex);
        this.at = complexString.substring(separatorIndex + TIME_SEPARATOR_LENGTH);
        this.isDone = false;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String At) {
        this.at = at;
    }

    @Override
    public String getStatusIcon() {
        String statusIcon = "[E]";
        return statusIcon + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s(at: %s)", this.getStatusIcon(), description, this.at);
    }
}
