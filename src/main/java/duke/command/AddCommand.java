package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

public class AddCommand extends Command{

    private final TaskType taskType;
    private final String arguments;

    public AddCommand(TaskType taskType, String arguments) {
        this.taskType = taskType;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = taskList.addTask(taskType, arguments);
            ui.output("Task added:", task);
        } catch (DukeException e) {
            e.handle(ui);
        }
    }
}
