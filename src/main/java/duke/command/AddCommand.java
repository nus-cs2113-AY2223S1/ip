package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

public class AddCommand extends Command{

    private TaskType taskType;
    private String arguments;

    public AddCommand(TaskType taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = TaskList.addTask(taskType, arguments);
            Ui.outputWithLines("Task added:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }
}
