package Duke.Commands;

import Duke.TaskList;

public class FindCommand extends Command {
    public static final String FIND_COMMAND = "find";
    protected String[] commandDescription;
    protected TaskList taskList;

    public FindCommand(String[] commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        taskList.findTask(commandDescription);
    }
}
