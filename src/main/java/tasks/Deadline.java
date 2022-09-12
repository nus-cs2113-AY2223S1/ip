package tasks;

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

    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "D|" + marked + "|" + super.descriptionString + "|" + this.by;
    }
}
