/**
 * Event class
 * extends the Task class to store a particular type of task (event)
 * contains a description of the task (String description)
 * contains a time when the event will take place (String at)
 */
public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
