/**
 * Creates an event of type task
 */
public class Event extends Task {
    protected String date;
    public Event(String description) {
        super(description);
        setDate(description);
    }

    public String getCommandIcon() { return "[E]"; }

    /**
     * Sets the date of the event
     *
     * @param description
     */
    public void setDate(String description) {
        int dateIndex = description.indexOf("/at") + 3;
        this.date = description.substring(dateIndex);

    }
    /**
     * Gets the date of the event
     *
     */
    public String getDate() { return date; }

    private String getDescriptionNoDate() {
        int dateIndex = description.indexOf("/at");
        return (description.substring(0, dateIndex - 1));
    }
    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + " " + getDescriptionNoDate().trim() + " (at: " + getDate().trim() + ")";
    }
    public String fileString(){
        return ( "E," + getDone() + "," + getDescriptionNoDate().trim() + "," + getDate());
    }
}
