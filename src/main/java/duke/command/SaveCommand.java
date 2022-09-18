package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.Ui;
import duke.exception.MissingDataFileDukeException;
import duke.task.TaskList;

import java.io.IOException;

public class SaveCommand extends Command{

    public SaveCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (TaskList.Tasks.size() > 0) {
            try {
                Storage.saveTasks();
                Ui.outputWithoutLines("Slaving tasks......");
                Ui.line();
            } catch (IOException e) {
                new MissingDataFileDukeException().handle();
                Ui.outputWithLines(e.getMessage());
            } catch (DukeException e) {
                e.handle();
            }
        }
    }
}
