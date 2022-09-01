public class Deadline extends Task {
    protected boolean isDeadlineCheck;

    public Deadline(String description) {
        super(description);
        this.isDeadlineCheck = false;
    }

    @Override
    public String getStatusOfTypeTask() {
        //mark Task with Deadline with "D"
        return (isDeadlineCheck ? "D" : " ");
    }

    @Override
    public void markTypeTask() {
        isDeadlineCheck = true;
    }
}
