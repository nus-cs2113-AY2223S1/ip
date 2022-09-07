public class Deadline extends Task implements FormatChecker {
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
     * Constructor for a raw string input of deadline
     *
     * @param complexString raw string containing description and deadline
     */
    public Deadline(String complexString) throws WrongCommandFormatException{
        super();
        try {
            int identifierIndex = FormatChecker.findIdentifierIndex(DEADLINE_IDENTIFIER, complexString);
            this.description = complexString.substring(0, identifierIndex);
            FormatChecker.checkNullString(this.description);
            this.by = complexString.substring(identifierIndex + TIME_IDENTIFIER_LENGTH);
            FormatChecker.checkNullString(this.by);
            this.isDone = false;
        } catch (WrongCommandFormatException e) {
            throw new WrongCommandFormatException();
        }
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
