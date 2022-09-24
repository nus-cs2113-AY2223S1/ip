package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int unmarkTaskIndex;

    public UnmarkCommand(int unmarkTaskIndex) {
        this.unmarkTaskIndex = unmarkTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsUndone(unmarkTaskIndex);
        ui.showTaskMarkAsUndoneMessage(taskList, unmarkTaskIndex);
        try {
            storage.updateDukeTextFile(unmarkTaskIndex, false);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
