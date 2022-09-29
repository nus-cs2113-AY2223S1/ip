package Duke.Commands;

import Duke.TaskList;

/**
 * When executed, it prints the list of tasks with description that match the keyword
 */
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
