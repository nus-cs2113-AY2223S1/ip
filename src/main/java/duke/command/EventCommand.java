
package duke.command;

import duke.data.task.Event;

public class EventCommand extends Command {
    public static final String COMMAND_NAME = "event";
    public static final String SYNTAX = "Syntax for event \n\t>>>event <description> / <date (can be in yyyy-mm-dd)>";
    public static final String MESSAGE = "Event added";
    public String description;
    public String date;

    public EventCommand(String description, String date) {
        super(COMMAND_NAME);
        this.description = description;
        this.date = date;
    }


    @Override
    public CommandResult execute() {
        Event added = new Event(description, date);
        this.taskList.data.add(added);
        target.add(added);
        return new CommandResult(MESSAGE, target);
    }
}
