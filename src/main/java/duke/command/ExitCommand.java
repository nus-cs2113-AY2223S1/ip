package duke.command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "exit";
    public static final String MESSAGE_TOP = "Bye!";

    /*Static */
    public static boolean isExit(Command command) {
        return command.commandType.equalsIgnoreCase(COMMAND_NAME);
    }

    /*Non-static */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_TOP);
    }

}
