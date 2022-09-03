package duke.error.exceptions.subcommand;

/**
 * Exception subclass of {@link SubCommandException} for if a subcommand
 * is not found.
 */
public class NoSubCommandException extends SubCommandException {
    /**
     * Constructor for exception
     *
     * @param command    command string
     * @param subCommand subcommand string
     */
    public NoSubCommandException(String command, String subCommand) {
        super(command, subCommand);
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("No valid subcommand found for command <%1$s>. "
                + "The subcommand <%2$s> is required. ", command, subCommand);
    }
}
