package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class UnmarkCommand extends Command{

    private final String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = taskList.markAsNotDone(taskNumber);
            ui.output("Boo! I've marked this task as not done yet:", task);
        } catch (DukeException e) {
            e.handle(ui);
        }
    }
}
