package duke.tasks;
/**
 * An Event represents an event at a certain time.
 * String <code>at</code> is the time task would be held at
 */
public class Event extends Task {
    private final String at;

    /** Constructors with and without isDone */
    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }
    public Event(String taskDescription, String at, boolean isDone) {
        super(taskDescription, isDone);
        this.at = at;
    }
    /** Getter function for <code>at</code>*/

    public String getAt() {
        return at;
    }

    /** Formatted <code>showTask</code> method specific for Events*/
    @Override
    public String showTask() {
        return "[E][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription + "(at: " + at + ")";
    }

    /** Formatted <code>writeTaskToFile</code> method specific for Events*/
    @Override
    public String writeTaskToFile(){
        return "E | " + (isDone ? 1 : 0) + " | " + taskDescription;
    }

}