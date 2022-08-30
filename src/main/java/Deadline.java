public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String complexString) {
        super();
        int i = complexString.indexOf("/");
        this.description = complexString.substring(0, i);
        this.by = complexString.substring(i + SEPARATOR_LENGTH);
        this.isDone = false;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        String statusIcon = "[D]";
        return statusIcon + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s(by: %s)", this.getStatusIcon(), description, this.by);
    }
}
