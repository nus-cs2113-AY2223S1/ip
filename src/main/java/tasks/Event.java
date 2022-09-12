package tasks;

public class Event extends Task {
    public String at;

    public Event(String descriptionString, String at) {
        super(descriptionString);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.descriptionString + " (at:" + this.at + ")";
    }

    @Override
    public String createFileString() {
        String marked = "N";
        if (this.isDone) {
            marked = "Y";
        }
        return "E|" + marked + "|" + super.descriptionString + "|" + this.at;
    }
}
