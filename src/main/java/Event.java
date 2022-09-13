public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isBlank()) {
            throw new DukeException();
        }
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

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + at;
    }
}
