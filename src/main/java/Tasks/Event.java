package Tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts task to string.
     * @return Task in string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Converts task to string to be saved.
     * @return String to be saved.
     */
    @Override
    public String toSave() {
        return "E," + super.toSave() + "," + at;
    }
}