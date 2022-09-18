package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskFactory;

/**
 * Command to add a new task.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates an Add command.
     * 
     * @param input The user input string
     * @throws DukeException Throws an exception if the user input format is invalid
     */
    public AddCommand(String input) throws DukeException {
        super(input);
        task = TaskFactory.createTask(input);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        taskList.addItem(task);
        ui.displayMessage("added: " + task);
        return taskList;
    }

}
