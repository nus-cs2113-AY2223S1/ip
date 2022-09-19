package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.Ui;
import duke.exception.MissingDataFileDukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents command for saving tasks into save file
 */
public class SaveCommand extends Command{

    public SaveCommand() {
    }

    /**
     * Saves tasks, if any, into save file
     * Informs user if tasks are successfully saved
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
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
