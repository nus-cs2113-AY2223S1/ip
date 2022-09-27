package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int deleteTaskIndex;

    public DeleteCommand(int deleteTaskIndex) {
        this.deleteTaskIndex = deleteTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (emptyListCheck(taskList, ui)) {
            return;
        }

        String deletedTaskDescription = String.valueOf(taskList.getTaskList().get(deleteTaskIndex));
        taskList.deleteTask(deleteTaskIndex);
        ui.showTaskDeletedMessage(taskList, deletedTaskDescription);

        try {
            storage.updateDukeTextFile(deleteTaskIndex, null);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }

    private static boolean emptyListCheck(TaskList taskList, Ui ui) {
        boolean isEmptyList = (taskList.getCurrentListSize() == 0);
        if (isEmptyList) {
            ui.showEmptyListMessage();
            return true;
        }
        return false;
    }
}
