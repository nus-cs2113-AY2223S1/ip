package duke.command;

import duke.data.TaskList;
import duke.data.Messages;

public class ListCommand extends Command {
    public ListCommand() {
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.DIVIDER_LIST, TaskList.list, TaskList.getTotalMessage());
    }

}
