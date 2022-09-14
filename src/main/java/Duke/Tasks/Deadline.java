package Duke.Tasks;
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, char taskType, String deadline) {
        super(description, taskType);
        this.deadline = deadline;
    }

    public String taskStatusWithDescriptionText() {
        String deadlineText = "(by: " + deadline + ")";
        return super.taskStatusWithDescriptionText() + deadlineText;
    }
}