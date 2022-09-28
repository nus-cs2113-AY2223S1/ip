package task.command;

import storage.Storage;
import task.Event;
import task.TaskList;
import ui.UI;

/**
 * A class that represents a event command.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Constructor for EventCommand.
     * @param event An event object
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command by adding an event into the list
     * and storing it in the local storage.
     * Displays a message telling user that the task has been added.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(event);
        ui.addMessage(event, tasks);
        storage.storeTask(event, storage);
    }
}
