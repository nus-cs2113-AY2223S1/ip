package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;
    public static final String COMMAND = "mark";
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        try {
            tasks.setTask(taskIndex, true);
            if(storage.isLoaded()) {
                ui.addLine(MESSAGE_MARK);
                ui.addLine(tasks.getMessages());
                ui.printUi();
            }
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }

        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
