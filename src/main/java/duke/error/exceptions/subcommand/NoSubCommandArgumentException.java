package duke.error.exceptions.subcommand;

/**
 * Exception subclass of {@link SubCommandException} for if there is no argument
 * after a subcommand string.
 */
public class NoSubCommandArgumentException extends SubCommandException {
    /**
     * Constructor for exception
     *
     * @param command    command string
     * @param subCommand subcommand string
     */
    public NoSubCommandArgumentException(String command, String subCommand) {
        super(command, subCommand);
    }

    /**
     * Message to be returned depending on exception.
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("The subcommand <%2$s> requires an argument after. "
                + "Please try the command <%1$s> again with an argument after "
                + "the subcommand <%2$s>. ", command, subCommand);
    }
}
