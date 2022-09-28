package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if a given string has
 * more words than it should.   <br>
 * E.g. {@code mark 1 3} has too many space-separated words (3).
 */
public class TooManyWordsException extends DukeException {
    private final String command;

    /**
     * Constructor for exception
     *
     * @param command command string
     */
    public TooManyWordsException(String command) {
        super();
        this.command = command;
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("The argument after <%s> must be a "
                + "single integer. Please try again", command);
    }
}
