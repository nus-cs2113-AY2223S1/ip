public class Event extends Task {
    private String datetime;

    public static final String SEPARATOR = "/at ";

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String getPrintString() {
        return String.format("[E]%s (at: %s)", super.getPrintString(), this.datetime);
    }
}
