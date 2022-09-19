package duke.taskings;


public class Deadline extends Task {

    protected String dateTime;

    public Deadline( String description, String dateTime, boolean isDone) {
        super(description,isDone);
        this.dateTime = dateTime;
        this.taskType = "D";
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return  super.toString() + " (by: " + dateTime + ")";
    }


}