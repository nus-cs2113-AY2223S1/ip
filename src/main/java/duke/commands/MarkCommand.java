package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Changes mark status of a task identified using it's last displayed index from the list to marked.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int markTaskIndex;

    /**
     * Constructs constructor for Mark command which stores the task index of task to be marked.
     *
     * @param markTaskIndex Task index to be marked.
     */
    public MarkCommand(int markTaskIndex) {
        this.markTaskIndex = markTaskIndex;
    }

    /**
     * Changes mark status of specified task by markTaskIndex in the task list to marked when the list is non-empty,
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

        taskList.markAsDone(markTaskIndex);
        ui.showTaskMarkAsDoneMessage(taskList, markTaskIndex);
        try {
            storage.updateDukeTextFile(markTaskIndex, true);
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
