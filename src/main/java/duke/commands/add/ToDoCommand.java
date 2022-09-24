package duke.commands.add;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    private final String taskName;

    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addToDoTask(taskName);
        ui.printTaskAddedMessage(taskList);
        try {
            storage.addTaskToDukeTextFile("T | 0 | " + taskName);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
