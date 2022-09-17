package duke.command;

public class TodoCommand extends Command{
    public TodoCommand(String description){
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "todo";
    public static final String SYNTAX = "Syntax for todo \n\t>>>todo <task>";
    /*Static */

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    
}
