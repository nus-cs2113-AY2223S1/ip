public class Deadline extends Task {
    /** To check if the task is a deadline task */
    protected boolean isDeadline;

    public Deadline(String description) {
        super(description);
        this.isDeadline = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark Task with Deadline with "D"
        return (isDeadline ? "D" : " ");
    }

    @Override
    public void markTypeTask() {
        isDeadline = true;
    }
}
