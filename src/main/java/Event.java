public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns formatted string
     *
     * @return string to print
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
