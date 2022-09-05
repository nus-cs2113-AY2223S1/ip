package duke;
public class Event extends Task{

    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String print() {
        return ("[E]" + super.print() + " (at: " + this.date + ")");
    }

    public String getDate() {
        return this.date;
    }

}