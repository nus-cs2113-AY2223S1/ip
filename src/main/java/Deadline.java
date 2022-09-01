public class Deadline extends Task {
    private String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getPrintString() {
        return String.format("[D]%s (by: %s)", super.getPrintString(), this.deadlineDate);
    }
}
