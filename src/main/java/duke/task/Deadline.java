package duke.task;
public class Deadline extends Task {
    public String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[D]";
    }

    public void printTask() {
        System.out.println(this.taskType + this.getStatusIcon() + " " + this.description + "\t(" + this.date + ")");
    }
}
