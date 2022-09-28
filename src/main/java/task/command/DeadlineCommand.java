package task.command;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.UI;

/**
 * A class that represents a deadline command.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param deadline A deadline object.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the deadline command by adding a deadline in the list
     * and storing it in the local storage.
     * Displays a message telling user that the task has been added.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(deadline);
        ui.addMessage(deadline, tasks);
        storage.storeTask(deadline, storage);
    }
}
