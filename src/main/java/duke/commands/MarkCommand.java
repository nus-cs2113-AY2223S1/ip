package duke.commands;

import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a mark command object that will execute the operations for Mark or Unmark command.
 */
public class MarkCommand extends UpdateCommand {
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    protected String command;

    /**
     * Initialises the variables of the MarkCommand class.
     *
     * @param command A string that indicates whether to mark or unmark a task.
     * @param description A string that represents the index of the task.
     */
    public MarkCommand(String command, String description) {
        super(description);
        this.command = command;
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
            // Sets boolean isDone variable to indicate whether the task is done or undone
            boolean isDone = command.equals(COMMAND_MARK);
            // Marks or unmark the task in the list
            String taskDetails = tasks.markTask(taskIndex, isDone);
            // Shows information for the task that has been marked or unmarked in the program.
            ui.showMarkTaskInfo(taskDetails, isDone);
            // Writes each task from the updated task list into the file
            writeToFile(ui, tasks, storage);
        } catch (NumberFormatException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_STATUS_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_TASK_INDEX);
        }
    }
}
