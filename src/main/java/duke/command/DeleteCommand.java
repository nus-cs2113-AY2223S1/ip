package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents command for deleting a task from taskList
 */
public class DeleteCommand extends Command{

    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Deletes a task from taskList after extracting task number from user input
     * Informs user if task deletion is successful
     * Informs user if task number is invalid
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = TaskList.deleteTask(taskNumber);
            Ui.outputWithLines("Task deleted:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }

}
