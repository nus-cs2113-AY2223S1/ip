package duke.command;

import duke.common.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

public class ListCommand extends Command {

    public ListCommand() {
        this.isExit = false;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        StringBuilder allTasks = new StringBuilder();

        if (tasks.isEmptyTaskList()) {
            ui.printEmptyTaskMessage();
            return;
        }

        String taskHeading = Message.TEXT_INDENTATION + "Here are the tasks in your list:\n";
        allTasks.append(taskHeading);
        for (int i = 0; i < tasks.getTaskSize(); ++i) {
            int numberedBullets = i + 1;
            Task curTask = tasks.getCurrentTask(i);
            String taskRow = Message.TEXT_INDENTATION + numberedBullets + ". " + curTask.getTaskInfo() + "\n";
            allTasks.append(taskRow);
        }
        ui.printMessage(String.valueOf(allTasks));
    }
}
