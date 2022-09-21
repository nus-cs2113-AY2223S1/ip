package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Lists all the tasks in Tasks
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    /**
     * Returns false as command is not ByeCommand
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
