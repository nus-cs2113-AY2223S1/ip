package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * When executed, it adds a event task to taskList
 */
public class AddEventCommand extends AddCommand {
    public static final String EVENT_COMMAND = "event";
    protected TaskList taskList;

    public AddEventCommand(String[] taskDescription, TaskList taskList) {
        super(taskDescription);
        TaskList.addEventTask(taskDescription);
        Storage.loadTasktoDataFile(taskList);
    }
}
