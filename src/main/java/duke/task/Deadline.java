package duke.task;
public class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String date) {
        super(description);
        this.byDate = date;
    }

    @Override
    public String toString() {
        return "[D][" + getIsDoneMarking() + "] " + getDescription() + " (by: " + byDate + ")";
    }

}
