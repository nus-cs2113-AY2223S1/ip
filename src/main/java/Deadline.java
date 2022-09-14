public class Deadline extends Task implements FormatChecker {
    private static final String DEADLINE_MARK = "[D]";
    private static final String taskType = "D";
    private String time;

    /**
     * Default constructor for Deadline instance
     *
     * @param description description of Deadline
     * @param time        time of deadline
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor for a raw string input of deadline
     *
     * @param complexString raw string containing description and deadline
     */
    public Deadline(String complexString) throws WrongCommandFormatException {
        super();
        try {
            int identifierIndex = FormatChecker.findIdentifierIndex(DEADLINE_IDENTIFIER, complexString);
            this.description = complexString.substring(0, identifierIndex);
            FormatChecker.checkNullString(this.description);
            this.time = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.time);
            this.isDone = false;
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
    }

    public Deadline(String description, Boolean isDone, String time) {
        this.description = description;
        this.isDone = isDone;
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return DEADLINE_MARK + super.getStatusIcon();
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
        return String.format("%s %s(by: %s)", this.getStatusIcon(), description, this.time);
    }
}
