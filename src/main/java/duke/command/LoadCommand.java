package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.LoadErrorDukeException;
import duke.exception.MissingDataFileDukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents command for loading tasks from save file
 */
public class LoadCommand extends Command {

    public LoadCommand() {
    }

    /**
     * Loads tasks, if any, from save file
     * Informs user if tasks are successfully loaded
     * Informs user if new save file is created
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.loadTasks(taskList);
            if (taskList.tasks.size() > 0) {
                ui.output("Remembering existing tasks......");
                ui.line();
                Command command = new ListCommand();
                command.execute(taskList, ui, storage);
                ui.line();
            }
        } catch (FileNotFoundException e) {
            try {
                storage.createDataFile();
                ui.output("Data file created under src/main/java/duke/data/data.txt");
            } catch (IOException ex) {
                new MissingDataFileDukeException().handle(ui);
            }
        } catch (DukeException e) {
            new LoadErrorDukeException().handle(ui);
        }
    }
}
