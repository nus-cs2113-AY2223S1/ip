package taskType;

/**
 * Event class is an extension of task, it stores an additional attribute, the time of
 * a task. Moreover, it also modifies the string representation of a event task.
 */
public class Event extends Task{
    protected String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String toString(){
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
