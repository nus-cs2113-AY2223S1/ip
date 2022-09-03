package duke.error.exceptions.subcommand;

/**
 * Exception subclass of {@link SubCommandException} for if there is more than
 * one subcommand in string.
 */
public class TooManySubCommandsException extends SubCommandException {
    /**
     * Constructor for exception
     *
     * @param command    command string
     * @param subCommand subcommand string
     */
    public TooManySubCommandsException(String command, String subCommand) {
        super(command, subCommand);
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("There was more than one of subcommand <%s> "
                + "detected. Command <%s> only takes 1 subcommand.", subCommand, command);
    }
}
