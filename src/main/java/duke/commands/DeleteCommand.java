package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deletes a task identified using it's last displayed index from the list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int deleteTaskIndex;

    /**
     * Constructs constructor for Delete command which stores the task index of task to be deleted.
     *
     * @param deleteTaskIndex Task index to be deleted.
     */
    public DeleteCommand(int deleteTaskIndex) {
        this.deleteTaskIndex = deleteTaskIndex;
    }

    /**
     * Deletes the task in the list specified by deleteTaskIndex when task list is non-empty,
     * prints confirmation message and updates duke.txt if successful.
     * Otherwise, ends function if list is empty or prints exception message if external error occurs.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print confirmation or exception messages.
     * @param storage Used to update task information in duke.txt.
     */
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
