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
            Storage.loadTasks();
            if (TaskList.Tasks.size() > 0) {
                Ui.outputWithoutLines("Remembering existing tasks......");
                Command command = new ListCommand();
                command.execute(taskList, ui, storage);
            }
        } catch (FileNotFoundException e) {
            try {
                Storage.createDataFile();
                Ui.outputWithLines("Data file created under src/main/java/duke/data/data.txt");
            } catch (IOException ex) {
                new MissingDataFileDukeException().handle();
            }
        } catch (DukeException e) {
            new LoadErrorDukeException().handle();
        }
    }
}
