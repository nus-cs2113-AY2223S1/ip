package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

/**
 * When executed, it marks the specified task as done
 */
public class MarkTaskCommand extends Command {
    public static final String MARK_COMMAND = "mark";
    protected TaskList taskList;
    protected String[] commandDescription;

    public MarkTaskCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.doMarkTask(commandDescription);
        Storage.updateTaskInDataFile(taskList, "edit");
    }
}
