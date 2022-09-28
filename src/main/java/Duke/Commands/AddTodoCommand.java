package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;

import java.io.IOException;

public class AddTodoCommand extends AddCommand {
    public static final String TODO_COMMAND = "todo";
    private String[] newTodoTask;
    protected TaskList taskList;

    public AddTodoCommand(String taskDescription, TaskList taskList) throws IOException {
        super(taskDescription);
        this.taskList = taskList;
        this.newTodoTask = new String[] {TODO_COMMAND, taskDescription};
        TaskList.addTodoTask(newTodoTask);
        Storage.loadTasktoDataFile(taskList);
    }
}
