package duke.taskmanager;

import duke.UI;
import duke.exceptions.LoadException;
import duke.taskmanager.commands.Command;
import duke.taskmanager.tasks.Task;

import java.util.ArrayList;

/**
 * Stores all the user's <code>task</code>s.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Creates an empty <code>TasList</code>.
     */
    public TaskList() {
    }

    /**
     * Load all the user's the previously saved <code>task</code>s.
     *
     * @param storage        where all the previously saved <code>task</code>s are stored
     * @throws LoadException when there is no previously saved <code>task</code>s
     */
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
