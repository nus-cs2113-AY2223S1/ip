package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

/**
 * When executed, it adds a deadline task to taskList
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String DEADLINE_COMMAND = "deadline";
    protected TaskList taskList;

    public AddDeadlineCommand(String[] taskDescription, TaskList taskList) {
        super(taskDescription);
        TaskList.addDeadlineTask(taskDescription);
        Storage.loadTasktoDataFile(taskList);
    }
}
