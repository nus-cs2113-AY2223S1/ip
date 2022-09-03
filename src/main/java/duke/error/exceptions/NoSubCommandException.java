package duke.error.exceptions;

public class NoSubCommandException extends SubCommandException {
    public NoSubCommandException(String command, String subCommand) {
        super(command, subCommand);
    }

    /**
     * Message to be used in dialog box
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("No valid subcommand found for command <%1$s>. "
                + "The subcommand <%2$s> is required. ", command, subCommand);
    }
}
