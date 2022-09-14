public class Event extends Task implements FormatChecker {
    private static final String EVENT_MARK = "[E]";
    private static final String taskType = "E";
    private String time;

    /**
     * Default constructor for Event instance
     *
     * @param description description of Event
     * @param time        time of event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
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
            this.time = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.time);
            this.isDone = false;
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
    }

    public Event(String description, Boolean isDone, String time) {
        this.description = description;
        this.isDone = isDone;
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return EVENT_MARK + super.getStatusIcon();
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s %s(at: %s)", this.getStatusIcon(), description, this.time);
    }
}
