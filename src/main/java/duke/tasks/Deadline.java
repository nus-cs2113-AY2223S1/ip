package duke.tasks;

public class Deadline extends Task{
    public String by;

    public Deadline(String descriptionString, String by) {
        super(descriptionString);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.descriptionString + " (by:" + this.by + ")";
    }
}
