package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND = "delete";
    protected TaskList taskList;
    protected String[] commandDescription;

    public DeleteCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.doDeleteTask(commandDescription);
        Storage.updateTaskInDataFile(taskList, "delete");
    }
}
