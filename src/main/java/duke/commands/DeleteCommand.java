package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a delete command object that will execute the operations for Delete command.
 */
public class DeleteCommand extends UpdateCommand {
    public static final String COMMAND = "delete";

    /**
     * Initialises the variables of the DeleteCommand class.
     *
     * @param description A string that represents the index of the task.
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            // Checks the index of a task to ensure that the tasks exist in the task list
            int taskIndex = checkIndexIsValid(description, tasks.getTaskCount());
            // Deletes the task from the task list
            String taskDetails = tasks.deleteTask(taskIndex);
            // Shows information for the task that has been deleted from the program
            ui.showDeleteTaskInfo(taskDetails, tasks.getTaskCount());
            // Writes each task from the updated task list into the file
            writeToFile(ui, tasks, storage);
        } catch (NumberFormatException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_DELETE_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TASK_INDEX);
        }
    }
}
