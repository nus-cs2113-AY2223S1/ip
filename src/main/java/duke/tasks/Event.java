package duke.tasks;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public String toString(){
        return "[E]"+ super.toString() + "(at: " + at + ")";
    }

}
