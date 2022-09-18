package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.LoadErrorDukeException;
import duke.exception.MissingDataFileDukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadCommand extends Command {

    public LoadCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.loadTasks(taskList);
            if (taskList.tasks.size() > 0) {
                ui.output("Remembering existing tasks......");
                Command command = new ListCommand();
                command.execute(taskList, ui, storage);
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
