
package duke.command;

import duke.data.tag.TaskList;
import duke.data.task.*;

import java.util.ArrayList;

public class EventCommand extends Command {
    public EventCommand(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }

    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX = "Syntax for event \n\t>>>event <task> / <date of event>";
    public static final String MESSAGE_TOP = "Event Added";

    public String description;
    public String date;
    public ArrayList<Task> target = new ArrayList<>();

    @Override
    public CommandResult execute() {
        Task added = new Event(description, date);
        this.taskList.add(added);
        target.add(added);
        return new CommandResult(MESSAGE_TOP, target, TaskList.getTotalMessage());
    }
}
