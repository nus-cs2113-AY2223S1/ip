package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Changes mark status of a task identified using it's last displayed index from the list to unmarked.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int unmarkTaskIndex;

    /**
     * Constructs constructor for Unmark command which stores the task index of task to be unmarked.
     *
     * @param unmarkTaskIndex Task index to be unmarked.
     */
    public UnmarkCommand(int unmarkTaskIndex) {
        this.unmarkTaskIndex = unmarkTaskIndex;
    }

    /**
     * Changes mark status of specified task by unmarkTaskIndex in the task list to unmarked when the list is non-empty,
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

        taskList.markAsUndone(unmarkTaskIndex);
        ui.showTaskMarkAsUndoneMessage(taskList, unmarkTaskIndex);
        try {
            storage.updateDukeTextFile(unmarkTaskIndex, false);
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
