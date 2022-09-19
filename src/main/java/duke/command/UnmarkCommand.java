package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents command for marking existing task as not done
 */
public class UnmarkCommand extends Command{

    private String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Mark task as not done after extracting task number from user input
     * Informs user if task unmarking is successful
     * Informs user if task number is invalid
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = TaskList.markAsNotDone(taskNumber);
            Ui.outputWithLines("Boo! I've marked this task as not done yet:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }
}
