package duke.util.asset;

public class Deadline extends Task {

    protected String byWhen;
    protected String optionFlag;

    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = byWhen;
        this.addMessage = "OH NO BEEP BEEP, a new Deadline: " + description;
        this.command = "deadline";
        this.optionFlag = "by";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }

    @Override
    public String serialize() {
        return command + " " + getTask() + "/" + optionFlag + " " + byWhen;
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }
}