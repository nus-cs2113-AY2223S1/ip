public class Event extends Task{
    /**
     * Creates Task Object
     *
     * @param description
     */
    protected String at;
    //protected String symbol;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        //symbol = "E";
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
