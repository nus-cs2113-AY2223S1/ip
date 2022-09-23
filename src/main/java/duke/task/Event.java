package duke.task;

/**
 * Represents the event.
 */
public class Event extends Task{
    protected String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }



    /**
     * Returns the information of the event for printing.
     *
     * @return Event in string format.
     */
    @Override
    public String getTaskInfo() {
        String statusIcon = isDone ? "X" : " ";
        return "[E]" +"[" + statusIcon + "] " + this.description +  " (at: " + this.time + ")";
    }

    /**
     * Sets the complete status of the event.
     *
     * @param isDone Complete status to be updated to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    /**
     * Returns the information of the event for storing.
     *
     * @return Event in string format.
     */
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("E | %d | %s | %s", isDoneInNumber, description, time) + System.lineSeparator();
    }
}
