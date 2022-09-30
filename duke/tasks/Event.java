package duke.tasks;
public class Event extends Task {
    protected String date;
    protected String initial = "E";

    public Event(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    /**
     * Returns all the information of the task as a string
     */
    public String toString() {
        return "[" + this.initial + "]" + super.toString() + " (by: " + this.date + ")";
    }
    public String getDate() { return this.date; }
}
