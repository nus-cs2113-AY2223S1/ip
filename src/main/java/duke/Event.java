package duke;

/**
 * Represents an event task
 */
public class Event extends Task{
    protected String datetime;

    public Event(String name, boolean isDone, char type, String datetime) {
        super(name, isDone, type);
        this.datetime = datetime;
    }
    @Override
    public String toString() {
        return super.toString() + " (at: " + datetime + ")";
    }
    @Override
    public String getDateTime() {
        return this.datetime;
    }


}
