package duke;

/**
 * Class representing a Deadline
 */
public class Deadline extends TaskList {
    /** Deadline inputted by user */
    protected String Deadline;
    /**
     * Constructor for Deadline to initialise it with description and Deadline passed in.
     *
     * @param description Description of what deadline is for.
     * @param dateDeadline Date of deadline.
     */
    public Deadline(String description, String dateDeadline) {
        super(description);
        this.dateDeadline = dateDeadline;
    }

    /**
     * Adds a new Deadline task.
     * @param name The name of task.
     * @param by A date
     * @return The generated Deadline Task.
     */
    public static Deadline AddTask(String name , String by){
        Deadline newDeadline = new Deadline(name,by);
        System.out.println(""+newDeadline.toString());
        return newDeadline;
    }

    @Override
    public String recordString() {
        return "D | " + super.recordString() + " | " + dateDeadline;
    }
    @Override
    public String toString() {
        return  "[D]" + "["+this.getStatusIcon()+"] " + this.name + " (by: " + dateDeadline + ")";
    }
}
