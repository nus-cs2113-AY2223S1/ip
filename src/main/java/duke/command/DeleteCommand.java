package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to remove a task from the list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a Delete command.
     * 
     * @param input The user input string
     */
    public DeleteCommand(String input) {
        super(input);
        index = Integer.parseInt(input.substring("delete".length()).trim());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        Task toDelete = lastResults.getItem(index);
        taskList.deleteItem(toDelete);
        ui.displayMessage(String.format("OK, I've deleted %s\n\n%s", toDelete, taskList.toString()));
        return taskList;
    }

}
