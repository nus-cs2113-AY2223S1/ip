package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int markTaskIndex;

    public MarkCommand(int markTaskIndex) {
        this.markTaskIndex = markTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(markTaskIndex);
        ui.showTaskMarkAsDoneMessage(taskList, markTaskIndex);
        try {
            storage.updateDukeTextFile(markTaskIndex, true);
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
