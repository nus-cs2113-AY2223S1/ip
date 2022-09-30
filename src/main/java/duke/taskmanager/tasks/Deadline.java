package duke.taskmanager.tasks;

/**
 * A <code>Deadline task</code> that has a <code>description</code> and <code>isDone</code>.
 * This <code>Deadline</code> has to be completed by an associated <code>date</code> and <code>time</code>.
 */
public class Deadline extends Todo {
    /**
     * Instantiate a new <code>Deadline</code> and splits the <code>String</code>
     * to find the associated date and time.
     *
     * @param command  user input in the form of "TASK_TYPE description ADDITIONAL_INFORMATION_FOR_DEADLINE_AND_EVENT"
     * @param lastChar '/' which represents a <code>event</code> or <code>deadline</code> command
     */
    public Deadline(String command, Character lastChar) {
        super(command, lastChar);
        int byStartIdx = command.indexOf('/') + "by _".length();
        command = command.substring(byStartIdx);
        findDateAndTime(command);
    }

    public String getBy() {
        return formatDate() + " " + this.time;
    }

    /**
     * <code>Deadline</code> is associated with '<code>D</code>'.
     *
     * @return the formatted type
     */
    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * Adds the formatted <code>date</code> and <code>time</code> that this
     * <code>Deadline</code> has to be completed by.
     *
     * @return formatted <code>String</code> representing this task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }


}
