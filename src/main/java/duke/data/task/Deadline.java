package duke.data.task;

public class Deadline extends Task {
    public static final String TYPE_DEADLINE = "D";

    public Deadline(String description, String date) {
        super(description);
        this.date = new Date(date);
        this.taskType = TYPE_DEADLINE;
    }

    @Override
    public String toString() {
        return (this.wrapType(TYPE_DEADLINE) + this.getStatusIcon() + " " + this.description.getData() + "\t("
                + this.date.getData() + ")");
    }

}
