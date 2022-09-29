package duke.taskmanager.commands;

import duke.UI;
import duke.exceptions.TaskOutOfBoundsException;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * Delete a specified <code>task</code>
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Deletes the <code>task</code> specified by user in the <code>TaskList</code>.
     * Checks if the user input an existing task.
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            int startIdx = "delete ".length();
            int pos = Integer.parseInt(this.userInput.substring(startIdx)) - 1;
            if (pos > tasks.size() || pos < 0) {
                throw new TaskOutOfBoundsException();
            }
            ui.printTaskAfterDelete(tasks,pos);
            tasks.remove(pos);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (TaskOutOfBoundsException e) {
            ui.printTaskOutOfBoundsException();
        }
    }

}
