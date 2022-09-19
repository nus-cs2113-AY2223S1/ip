package duke.command;

import duke.storage.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents command for marking existing task as done
 */
public class MarkCommand extends Command {

    private String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Mark task as done after extracting task number from user input
     * Informs user if task marking is successful
     * Informs user if task number is invalid
     * @param taskList ArrayList containing current tasks
     * @param ui Ui object for communicating with user
     * @param storage Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskNumber = extractTaskNumber(arguments);
            String task = TaskList.markAsDone(taskNumber);
            Ui.outputWithLines("Well done. I've marked this task as done:", task);
        } catch (DukeException e) {
            e.handle();
        }
    }

}
