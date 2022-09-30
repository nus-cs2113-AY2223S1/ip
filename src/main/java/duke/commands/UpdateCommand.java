package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents an update command object that will be inherited by delete and mark command objects.
 */
public class UpdateCommand extends Command {
    protected String description;

    /**
     * Initialises the variables of the UpdateCommand class.
     *
     * @param description A string that represents the index of the task.
     */
    public UpdateCommand(String description) {
        this.description = description;
    }

    /**
     * Checks the index of a task to ensure that the tasks exist in the task list.
     *
     * @param description A string that represents the index of a task.
     * @param taskCount An integer that states the current number of tasks in the task list.
     * @return An integer that represents the index of a task.
     * @throws NumberFormatException If the task index is not within the range of available indexes.
     */
    public int checkIndexIsValid(String description, int taskCount) throws NumberFormatException {
        // Decrements the user input of index as the task list starts from 0
        int taskIndex = Integer.parseInt(description) - 1;
        // Checks that the task index is within the range of available indexes
        if (taskIndex < 0 || taskIndex > taskCount) {
            throw new IndexOutOfBoundsException();
        }
        return taskIndex;
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    public void execute(TextUi ui, TaskList tasks, Storage storage) {}
}
