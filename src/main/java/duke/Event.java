package duke;

public class Event extends Task {
    protected String at;

    public Event(String description,boolean isDone, String at) {
        super(description,isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("E|" + isDoneValue + "|" + this.task + "|" + this.at +"\n");
        return taskDetails;
    }
}
