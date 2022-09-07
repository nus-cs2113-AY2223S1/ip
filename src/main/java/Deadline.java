public class Deadline extends Task {
    /** To check if the task is a deadline task */
    protected boolean isDeadline;

    public Deadline(String description) {
        super(description);
        this.isDeadline = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        String status = " ";
        if (isDeadline) {
            status = "D";
        }
        return status;
    }

    @Override
    public void markTypeTask() {
        isDeadline = true;
    }
}
