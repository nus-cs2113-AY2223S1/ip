package duke.command;

public class IncorrectCommand extends Command{
    public IncorrectCommand(String message){
        super(COMMAND_NAME);
        this
    }

    /*Variables*/
    public static final String COMMAND_NAME = null;
    

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    
}
