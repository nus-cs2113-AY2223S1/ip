package duke.command;

public class MarkCommand extends Command {
    public MarkCommand() {
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "mark";
    public static final String SYNTAX =
    public static final String ERROR_MESSAGE_MARK = "Syntax for (un)mark \n\t>>> (un)mark <item index number> \nNote: item index must exist in the current list";
    /*Static */


    /*Non-static */
    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult();
        return result;
    }
}
