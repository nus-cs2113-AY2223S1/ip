package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

public class MarkTaskCommand extends Command {
    protected TaskList taskList;
    protected String[] commandDescription;

    public MarkTaskCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.doMarkTask(commandDescription);
        Storage.updateTaskInDataFile(taskList, "edit");
    }
}
