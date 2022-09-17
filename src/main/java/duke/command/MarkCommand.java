package duke.command;


import duke.data.task.*;
import java.util.ArrayList;

public class MarkCommand extends Command {
    public MarkCommand(Integer... index) {
        super(COMMAND_NAME);
        this.index = index;
    }

    /* Variables */
    public static final String COMMAND_NAME = "mark";
    public static final String SYNTAX = "Syntax for mark\n\t>>> mark <item index number>\nNote: item index must exist in the current list";
    public static final String MESSAGES_TOP = "These tasks are marked";

    public Integer[] index;
    public ArrayList<Task> target = new ArrayList<Task>();

    @Override
    public CommandResult execute() {
        



        CommandResult result = new CommandResult(MESSAGES_TOP,target,"");
        return result;
    }
}
