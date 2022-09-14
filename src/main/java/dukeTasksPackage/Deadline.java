package dukeTasksPackage;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        this.description = description;
        this.status = status;
        this.by = by;
    }

    @Override
    public String toString() {
        return "   [D]" + " [" + status + "] " + description + "(by: " + by + ")";
    }
    @Override
    public String toFileString() {
        return "D | " + status + " | " + description + "| " + by + System.lineSeparator();
    }
}

