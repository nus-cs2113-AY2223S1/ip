public class Event extends Task implements FormatChecker{
    private static final String EVENT_MARK = "[E]";
    private String at;

    /**
     * Default constructor for Event instance
     *
     * @param description description of Event
     * @param at time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Event constructor for a raw string input of event
     *
     * @param complexString raw string input containing time and description of event
     */
    public Event(String complexString) throws WrongCommandFormatException {
        super();
        try {
            int identifierIndex = FormatChecker.findIdentifierIndex(EVENT_IDENTIFIER, complexString);
            this.description = complexString.substring(0, identifierIndex);
            FormatChecker.checkNullString(this.description);
            this.at = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.at);
            this.isDone = false;
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String getStatusIcon() {
        return EVENT_MARK + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("%s %s(at: %s)", this.getStatusIcon(), description, this.at);
    }
}
