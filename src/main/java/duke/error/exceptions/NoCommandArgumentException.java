package duke.error.exceptions;

/**
 * Exception subclass of {@link DukeException} for when an argument is needed after
 * a command but none are given.
 */
public class NoCommandArgumentException extends DukeException {
    private final String command;

    /**
     * Constructor for exception
     *
     * @param command command string
     */
    public NoCommandArgumentException(String command) {
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
        return String.format("The command <%s> requires an argument after. "
                + "Please try again with an argument after the command.", command);
    }
}
