package duke.error.exceptions;

public class NoSubCommandArgumentException extends SubCommandException {
    public NoSubCommandArgumentException(String firstWord, String subCommand) {
        super(firstWord, subCommand);
    }

    /**
     * Message to be used in dialog box
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
