package Duke.Commands;

import Duke.TaskList;

public class FindCommand extends Command {
    protected String[] commandDescription;
    protected TaskList taskList;

    public FindCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.findTask(commandDescription);
    }
}
