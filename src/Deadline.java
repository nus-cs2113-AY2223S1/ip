public class Deadline extends Task {
    String by;
    public Deadline(String description, String dueDate, boolean status) {
        super(description, status);
        by = dueDate;
    }

    @Override
    public String getDescription() {
        return this.description + this.by;
    }

    @Override
    public String getDescriptionAndStatus() {
        return "[D][" + this.getStatus() + "] " + this.getDescription();
    }
    @Override
    public String fileFormat() {
        return "D ┊ " + this.getStatus() + " ┊ " + this.description + " ┊ " + this.by;
    }
}