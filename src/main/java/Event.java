public class Event extends Task{
    /**
     * Creates Task Object
     *
     * @param description
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
