package duke.taskmanager;

import duke.UI;
import duke.exceptions.LoadException;
import duke.taskmanager.commands.Command;
import duke.taskmanager.tasks.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Creates an empty <code>TasList</code> with a buffer <code>todo</code> at index 0 so the index will be one based
     */
    public TaskList() {

    }

    public TaskList(Storage storage) throws LoadException {
        if (storage.storedTasks.isEmpty()) {
            throw new LoadException();
        }
        UI ui = new UI();
        for (String command : storage.storedTasks) {
            Command c = Parser.parser(command);
            c.execute(this, ui,storage);
        }
    }
}
