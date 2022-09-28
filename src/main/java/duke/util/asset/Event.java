package duke.util.asset;

public class Event extends Task {

    protected String atTiming;
    protected String optionFlag;

    public Event(String description, String atTiming) {
        super(description);
        this.atTiming = atTiming;
        this.addMessage = "OH NO BEEP BEEP, a new Event: " + description;
        this.command = "event";
        this.optionFlag = "at";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atTiming + ")";
    }

    @Override
    public String serialize() {
        return command + " " + getTask() + "/" + optionFlag + " " + atTiming;
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }

}