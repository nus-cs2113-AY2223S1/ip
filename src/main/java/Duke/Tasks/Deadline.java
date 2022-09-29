package Duke.Tasks;

public class Deadline extends Tasks {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.description + " (by: " + this.by + ")";
    }

    @Override
    public String toFile() {
        return "D|" + ((this.isDone) ? 1 : 0) + "|" + super.description + " | " + this.by + "\n";
    }


}