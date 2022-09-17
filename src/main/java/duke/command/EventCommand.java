
package duke.command;

public class EventCommand extends Command{
    public EventCommand(){
        super(COMMAND_NAME);
    }

    /*Variables*/
    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX ="Syntax for event \n\t>>>event <task> / <date of event>"
    /*Static */

    /*Non-static */
    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult();
        return result;
    } 
    
}
