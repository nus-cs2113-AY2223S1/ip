package duke.command;

import duke.common.Messages;

public class ListCommand extends Command {
    public ListCommand() {
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "list";

    @Override
    public CommandResult execute() {
        this.target = this.taskList.data;
        return new CommandResult(Messages.DIVIDER_LIST, this.target);
    }

}
