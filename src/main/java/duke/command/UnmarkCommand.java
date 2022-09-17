package duke.command;

import duke.data.task.*;
import java.util.ArrayList;


public class UnmarkCommand extends Command{
    public UnmarkCommand(Integer... index){
        super(COMMAND_NAME);
        this.index = index;
    }

    public static final String COMMAND_NAME = "unmark";
    public static final String SYNTAX ="Syntax for unmark\n\t>>> unmark <item index number>\nNote: item index must exist in the current list";
    public static final String MESSAGE_TOP = "These tasks are marked";

    public Integer[] index;
    public ArrayList<Task> target = new ArrayList<Task>();

    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult(MESSAGE_TOP,target,"");
        return result;
    } 
    
}
