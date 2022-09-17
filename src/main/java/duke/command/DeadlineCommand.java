package duke.command;

public class DeadlineCommand extends Command{
    public DeadlineCommand(){
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "deadline";
    public static final String SYNTAX ="Syntax for deadline\n\t>>>deadline <task> / <date of deadline>";
    /*Static */

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    
}
