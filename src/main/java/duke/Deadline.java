package duke;

/**
 * Represents a deadline task
 */
public class Deadline extends Task{
    protected String datetime;

    public Deadline(String name, boolean isDone, char type, String datetime) {
        super(name, isDone, type);
        this.datetime = datetime;
    }
    @Override
    public String getDateTime() {
        return this.datetime;
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + datetime + ")";
    }


}
