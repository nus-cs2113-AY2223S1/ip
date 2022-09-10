package duke;

public class Deadline extends Task{
    protected String datetime;

    public Deadline(String name, boolean isDone, char type, String datetime) {
        super(name, isDone, type);
        this.datetime = datetime;
    }
    public String getDateTime() {
        return this.datetime;
    }
    public String toString() {
        return super.toString() + " (by: " + datetime + ")";
    }


}
