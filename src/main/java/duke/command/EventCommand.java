
package duke.command;

import duke.data.task.*;

import java.util.ArrayList;

import duke.data.TaskList;

public class EventCommand extends Command {
    public EventCommand(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    /*Variables*/
    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX = "Syntax for event \n\t>>>event <task> / <date of event>";
    public static final String MESSAGE_TOP = "Event Added";

    public String description;
    public String date;
    public ArrayList<Task> target = new ArrayList<>();

    /*Non-static */
    @Override
    public CommandResult execute() {
        Task added = new Event(description, date);
        TaskList.list.add(added);
        target.add(added);
        CommandResult result = new CommandResult(MESSAGE_TOP, target, TaskList.getTotalMessage());
        return result;
    }
}
