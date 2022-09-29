package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Event;

/**
 * Represent a command with command word, description and time of occurrence specified.
 * There is also a Event initiated which is used in the execute method to be added to task list.
 * Used when command word is 'event'.
 */
public class AddEventCommand extends AddCommand {

    protected String at;
    protected Event toAdd;

    public AddEventCommand(String commandWord, String taskline, String at) {
        super(commandWord, taskline);
        this.at = at;
        this.toAdd = new Event(taskline, at);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addEvent(toAdd, storage);
    }
}
