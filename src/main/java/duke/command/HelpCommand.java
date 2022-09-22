package duke.command;

import duke.common.Messages;

public class HelpCommand extends Command {
    public static final String COMMAND_NAME = "help";

    public HelpCommand() {
        super(COMMAND_NAME);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.COMMAND_LISTS);
    }
}
