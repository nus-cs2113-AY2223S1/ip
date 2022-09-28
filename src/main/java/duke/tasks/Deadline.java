package duke.tasks;

/**
 * A Deadline represents a task with a deadline.
 * String <code>by</code> is the time task should be completed by
 */
public class Deadline extends Task {
    private final String by;

    /** Constructors with and without isDone */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }
    public Deadline(String taskDescription, String by, boolean isDone) {
        super(taskDescription, isDone);
        this.by = by;
    }
    /** Getter function for <code>by</code>*/
    public String getBy() {
        return by;
    }

    /** Formatted <code>showTask</code> method specific for Deadline*/
    @Override
    public String showTask() {
        return "[D][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription + "(by: " + by + ")";
    }
    /** Formatted <code>writeTaskToFile</code> method specific for Deadline*/
    @Override
    public String writeTaskToFile(){
        return "D | " + (isDone ? 1 : 0) + " | " + taskDescription;
    }

}
