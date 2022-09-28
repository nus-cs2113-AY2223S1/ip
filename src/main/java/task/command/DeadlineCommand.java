package task.command;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.UI;

public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(deadline);
        ui.addMessage(deadline, tasks);
        tasks.storeTask(deadline, storage);
    }
}
