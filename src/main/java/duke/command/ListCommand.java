package duke.command;

import duke.common.Messages;

public class ListCommand extends Command {
    public ListCommand() {
        super(COMMAND_NAME);
        this.target = this.taskList.data;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.DIVIDER_LIST, this.taskList.data);
    }

}
