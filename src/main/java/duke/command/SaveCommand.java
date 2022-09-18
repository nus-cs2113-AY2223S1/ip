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
        if (taskList.tasks.size() > 0) {
            try {
                storage.saveTasks(taskList);
                ui.output("Slaving tasks......");
                ui.line();
            } catch (IOException e) {
                new MissingDataFileDukeException().handle(ui);
                ui.output(e.getMessage());
            } catch (DukeException e) {
                e.handle(ui);
            }
        }
    }
}
