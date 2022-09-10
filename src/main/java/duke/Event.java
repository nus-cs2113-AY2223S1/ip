package duke;

public class Event extends Task{
    protected String datetime;

    public Event(String name, boolean isDone, char type, String datetime) {
        super(name, isDone, type);
        this.datetime = datetime;
    }
    public String toString() {
        return super.toString() + " (at: " + datetime + ")";
    }

    @Override
    public String getDateTime() {
        return this.datetime;
    }


}
