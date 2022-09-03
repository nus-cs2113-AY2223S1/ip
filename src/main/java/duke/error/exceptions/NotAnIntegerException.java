package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for if a given string is not an
 * integer but should be.
 */
public class NotAnIntegerException extends DukeException {
    private final String command;

    /**
     * Constructor for exception
     *
     * @param command command string
     */
    public NotAnIntegerException(String command) {
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
        return String.format("The argument after <%s> was not an integer. "
                + "Please try again.", command);
    }
}
