package duke.command;

public class ListCommand extends Command {
    public ListCommand(){
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "list";

    /*Static */

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    

}
