
package duke.command;

import duke.data.task.*;
import java.util.ArrayList;
import duke.data.TaskList;

public class EventCommand extends Command{
    public EventCommand(String description, String date){
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX ="Syntax for event \n\t>>>event <task> / <date of event>";
    public static final String MESSAGE_TOP = "Event Added";
    public String messageBottom = "You have " + TaskList.list.size() + " tasks.";

    public String description;
    public String date;

    /*Non-static */
    @Override
    public CommandResult execute(){
        Task added = new Event(description, date);
        TaskList.list.add(added);
        ArrayList<Task> target = new ArrayList<Task>();
        target.add(added);
        CommandResult result = new CommandResult(MESSAGE_TOP, target , messageBottom);
        return result;
    } 
}
