package duke.taskmanager;

import duke.UI;
import duke.exceptions.LoadException;
import duke.taskmanager.commands.Command;
import duke.taskmanager.tasks.Task;
import duke.taskmanager.tasks.Todo;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        this.add(new Todo("Todo buffer for one based input", ' '));
    }

    public TaskList(Storage storage) throws LoadException {
        if (storage.storedTasks.isEmpty()) {
            throw new LoadException();
        }
        this.add(new Todo("Todo buffer for one based input", ' '));
        TaskList previousTasks = new TaskList();
        previousTasks.remove(0);
        for (String userInput : storage.storedTasks) {
            Command c = Parser.parser(userInput);
            UI ui = new UI();
            c.execute(previousTasks, ui,storage);
        }
        this.addAll(previousTasks);
    }
}
