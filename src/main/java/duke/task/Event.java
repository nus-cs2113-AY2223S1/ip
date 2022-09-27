package duke.task;

public class Event extends Task {

    String at;

    /**
     * Initializes aa Event class
     * @param description What the event is about
     * @param at Time or Date the event is stated to start
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String toSaveString(){return "[E]" + super.toString() + "/at " + at;}

    public String getType(){
        return "E";
    }
}