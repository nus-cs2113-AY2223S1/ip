package duke.command;

public class DeleteCommand extends Command {
    public DeleteCommand(){
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "Syntax for delete \n\t>>> delete <item index number> \nNote: item index must exist in the current list";
    /*Static */

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    
}
