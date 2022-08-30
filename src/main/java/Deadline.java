public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, char taskType, String deadline) {
        super(description, taskType);
        this.deadline = deadline;
    }

    public String printTask() {
        String deadlineText = "(by: " + deadline + ")";
        return super.printTask() + deadlineText;
    }
}