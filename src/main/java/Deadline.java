public class Deadline extends Task {
    /**
     * Creates Task Object
     *
     * @param text
     */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
