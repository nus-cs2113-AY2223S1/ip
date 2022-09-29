package task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("  " + this);
    }

    public Event(String description, String at, boolean status) {
        super(description, status);
        this.at = at;
        System.out.println("  " + this);
    }

    public String getAt(){
        return at;
    }


    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";

    }
}