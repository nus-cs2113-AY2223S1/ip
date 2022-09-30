package task;

/**
 * in charge of type event of the task
 */
public class Event extends Task {
    protected String at;

    /**
     * used when both description and time of the event are given
     *
     * @param description description of the task
     * @param at time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("  " + this);
    }

    /**
     * used when description, time of the event, and status are given
     *
     * @param description description of the task
     * @param at time of the event
     * @param status the status of the task
     */
    public Event(String description, String at, boolean status) {
        super(description, status);
        this.at = at;
        System.out.println("  " + this);
    }

    public String getAt(){
        return at;
    }


    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";

    }
}