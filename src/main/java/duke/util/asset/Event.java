package duke.util.asset;

public class Event extends Task {

    protected String atTiming;
    public static final String OPTIONFLAG = "at";
    public static final String COMMAND = "event";
    public static final String MESSAGE_HELP = "event [TASK] /at [DATE]\t "
            + "- add a task that start at a specific date and ends at a specific date";

    public Event(String description, String atTiming) {
        super(description);
        this.atTiming = atTiming;
        this.addMessage = "OH NO BEEP BEEP, a new Event: " + description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atTiming + ")";
    }

    @Override
    public String serialize() {
        return COMMAND + " " + getTask() + "/" + OPTIONFLAG + " " + atTiming;
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }

}