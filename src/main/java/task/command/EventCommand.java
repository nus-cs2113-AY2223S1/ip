package task.command;

import storage.Storage;
import task.Event;
import task.TaskList;
import ui.UI;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(event);
        ui.addMessage(event, tasks);
        tasks.storeTask(event, storage);
    }
}
