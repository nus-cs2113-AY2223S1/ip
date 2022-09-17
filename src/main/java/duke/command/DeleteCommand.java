package duke.command;

import duke.data.task.*;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(Integer... index){
        super(COMMAND_NAME);
        this.index = index;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "Syntax for delete\n\t>>> delete <item index number>\nNote: item index must exist in the current list";
    public static final String MESSAGE_TOP = "These tasks are marked done";

    public Integer[] index;
    public ArrayList<Task> target = new ArrayList<Task>();


    @Override
    public CommandResult execute(){
        CommandResult result = new CommandResult(MESSAGE_TOP,target,"");
        return result;
    } 
    
}
