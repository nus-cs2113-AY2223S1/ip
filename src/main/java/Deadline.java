public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isBlank()) {
            throw new DukeException();
        }
        this.by = by;
    }

    /**
     * Returns formatted string
     *
     * @return string to print
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + by;
    }
}

