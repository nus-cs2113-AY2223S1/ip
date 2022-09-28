package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

public class UnmarkTaskCommand extends Command {
    protected TaskList taskList;
    protected String[] commandDescription;

    public UnmarkTaskCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.doUnmarkTask(commandDescription);
        Storage.updateTaskInDataFile(taskList, "edit");
    }
}
