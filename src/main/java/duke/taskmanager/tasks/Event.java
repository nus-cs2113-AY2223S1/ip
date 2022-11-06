package duke.taskmanager.tasks;

/**
 * A <code>Event task</code> that has a <code>description</code> and <code>isDone</code>.
 * This <code>Event</code> occurs at associated <code>date</code> and <code>time</code>.
 */
public class Event extends Todo {
    /**
     * Instantiate a new <code>Event</code> and splits the <code>String</code>
     * to find the associated date and time.
     *
     * @param command  user input in the form of "TASK_TYPE description ADDITIONAL_INFORMATION_FOR_DEADLINE_AND_EVENT"
     * @param lastChar '/' which represents a <code>event</code> or <code>deadline</code> command
     */
    public Event(String command, Character lastChar) {
        super(command, lastChar);
        int atStartIdx = command.indexOf('/') + "at _".length();
        command = command.substring(atStartIdx);
        findDateAndTime(command);
    }

    public String getAt() {
        return formatDate() + " " + this.time;
    }

    /**
     * <code>Event</code> is associated with '<code>E</code>`.
     *
     * @return the formatted type
     */
    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * Adds the formatted <code>date</code> and <code>time</code> that this
     * <code>Event</code> occurs at.
     *
     * @return formatted <code>String</code> representing this task
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getAt() + ")";
    }
}
