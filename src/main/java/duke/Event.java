package duke;

public class Event extends TaskList {

    protected String at;


    /**
     * Constructs a Event object given description string and datetime string.
     * @param description The name of task.
     * @param at A datetime
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Adds a new Event task.
     * @param name The name of task.
     * @param at A datetime
     * @return The generated Event Task.
     */
    public static Event AddEvent(String name , String at){
        Event newEvent = new Event(name,at);
        System.out.println(""+newEvent.toString());
        return newEvent;
    }

    @Override
    public String recordString() {
        return "E | " + super.recordString() + " | " + at;
    }

    @Override
    public String toString() {
        return   "[E]" + "["+this.getStatusIcon()+"] " + this.name + " (by: " + at + ")";
    }
}
