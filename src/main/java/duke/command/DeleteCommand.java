package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class DeleteCommand extends Command{

    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = TaskList.deleteTask(taskNumber);
            Ui.outputWithLines("Task deleted:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }

}
