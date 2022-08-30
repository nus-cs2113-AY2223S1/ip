
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        this.description = description;
        this.status = status;
        this.by = by;
        this.alphabet = 'D';
    }

    @Override
    public String toString() {
        return "   [D]" + " [" + status + "] " + description + "(by: " + by + ")";
    }
}

