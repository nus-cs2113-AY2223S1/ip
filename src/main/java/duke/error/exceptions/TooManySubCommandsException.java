package duke.error.exceptions;

public class TooManySubCommandsException extends SubCommandException {
    public TooManySubCommandsException(String command, String subCommand) {
        super(command, subCommand);
    }

    /**
     * Message to be used in dialog box
     *
     * @return message string
     */
    @Override
    public String getExceptionMessage() {
        return String.format("There was more than one of subcommand <%s> "
                + "detected. Command <%s> only takes 1 subcommand.", subCommand, command);
    }
}
