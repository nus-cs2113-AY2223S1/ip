package duke.data.task;

/**
 * Represent the Deadline Task
 */
public class Deadline extends Task {
    public static final String TYPE_DEADLINE = "D";

    public Deadline(String description, String date) {
        super(description);
        this.date = new Date(date);
        this.taskType = TYPE_DEADLINE;
    }

    /**
     * Initiate with isDone
     */
    public Deadline(Boolean isDone, String description, String date) {
        this(description, date);
        this.setIsDone(isDone);
    }

    @Override
    public String toString() {
        return (this.wrapType(TYPE_DEADLINE) + this.getStatusIcon() + " " + this.description.getData() + "\t("
                + this.date.getData() + ")");
    }

}
