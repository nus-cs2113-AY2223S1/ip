public class Deadline extends Task {
    private static final String DEADLINE_MARK = "[D]";
    private String by;

    /**
     * Default constructor for Deadline instance
     *
     * @param description description of Deadline
     * @param by time of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for a raw string input of dealine
     *
     * @param complexString raw string containing description and dealine
     */
    public Deadline(String complexString) {
        super();
        int separatorIndex = complexString.indexOf(TIME_SEPARATOR);
        this.description = complexString.substring(0, separatorIndex);
        this.by = complexString.substring(separatorIndex + TIME_SEPARATOR_LENGTH);
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
        return DEADLINE_MARK + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s(by: %s)", this.getStatusIcon(), description, this.by);
    }
}
