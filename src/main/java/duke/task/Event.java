package duke.task;

public class Event extends Task {

    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String toSaveString(){return "[E]" + super.toString() + "/at " + at;}

    public String getType(){
        return "E";
    }
}