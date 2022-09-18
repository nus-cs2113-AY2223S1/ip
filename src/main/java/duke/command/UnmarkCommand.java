package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class UnmarkCommand extends Command{

    private String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = TaskList.markAsNotDone(taskNumber);
            Ui.outputWithLines("Boo! I've marked this task as not done yet:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }
}
