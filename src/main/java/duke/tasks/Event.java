package duke.tasks;

/**
 * Represents the task of type Event
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    /**
     * Sets the time of the event
     *
     * @param at the time of the event
     */
    public void setAt(String at) {
        this.at = at;
    }


    /**
     * Gets the time of the event
     *
     * @return the time of the event.
     */
    public String getAt() {
        return at;
    }


    public String toString(){
        return "[E]"+ super.toString() + "(at: " + at + ")";
    }

}
