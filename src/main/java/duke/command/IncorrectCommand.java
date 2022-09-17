package duke.command;

public class IncorrectCommand extends Command{
    public IncorrectCommand(String message){
        super(COMMAND_NAME);
        this.message = message;
    }


    public static final String COMMAND_NAME = "incorrect";
    public String message;
    
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult(this.message);
        return result;
    } 
    
}
