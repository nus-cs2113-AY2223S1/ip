package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

import java.io.IOException;

/**
 * When executed, it adds a deadline task to taskList
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String DEADLINE_COMMAND = "deadline";
    private String[] newDeadlineTask;
    protected TaskList taskList;

    public AddDeadlineCommand(String taskDescription, TaskList taskList) throws IOException {
        super(taskDescription);
        this.taskList = taskList;
        this.newDeadlineTask = new String[] {DEADLINE_COMMAND, taskDescription};
        TaskList.addDeadlineTask(newDeadlineTask);
        Storage.loadTasktoDataFile(taskList);
    }
}
