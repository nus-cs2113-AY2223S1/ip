package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

/**
 * When executed, it adds a todo task to taskList
 */
public class AddTodoCommand extends AddCommand {
    public static final String TODO_COMMAND = "todo";
    protected TaskList taskList;

    public AddTodoCommand(String[] taskDescription, TaskList taskList) {
        super(taskDescription);
        TaskList.addTodoTask(taskDescription);
        Storage.loadTasktoDataFile(taskList);
    }
}
