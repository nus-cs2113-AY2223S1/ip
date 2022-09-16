package duke.tasks;

public class Deadline extends Task {
    protected String date;
    protected String initial = "D";

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.initial + "]" + super.toString() + " (by: " + this.date + ")";
    }
    public String getDate() { return this.date; }
}
