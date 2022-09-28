package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

import java.io.IOException;

public class AddEventCommand extends AddCommand {
    public static final String EVENT_COMMAND = "event";
    private String[] newEventTask;
    protected TaskList taskList;

    public AddEventCommand(String taskDescription, TaskList taskList) throws IOException {
        super(taskDescription);
        this.taskList = taskList;
        this.newEventTask = new String[] {EVENT_COMMAND, taskDescription};
        TaskList.addEventTask(newEventTask);
        Storage.loadTasktoDataFile(taskList);
    }
}
