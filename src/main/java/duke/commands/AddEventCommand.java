package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Event;

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
