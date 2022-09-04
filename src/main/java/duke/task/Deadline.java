package duke.task;
public class Deadline extends Task {
    private static final String TYPE_DEADLINE = "[D]";
    public String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = TYPE_DEADLINE;
    }

    public void printTask() {
        System.out.println(this.taskType + this.getStatusIcon() + " " + this.description + "\t(" + this.date + ")");
    }
}
