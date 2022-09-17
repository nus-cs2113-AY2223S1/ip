package duke.taskings;


public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String taskType, String description, boolean isDone, String dateTime) {
        super(taskType, description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Added deadline: [D] " + super.toString() + " (by: " + dateTime + ")";
    }

    @Override
    public String printList() {
        return "[D] [" + super.getStatusIcon() + "] " + super.description + " (by: " + dateTime + ")";
    }
}