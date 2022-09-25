package duke.taskmanager.commands;

import duke.UI;
import duke.exceptions.TaskOutOfBoundsException;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Deletes the task specified by user. Checks if the user input an existing task.
     *
     * @param tasks   list that stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage to store the tasks after the programme is closed
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            int startIdx = "delete ".length();
            int pos = Integer.parseInt(this.userInput.substring(startIdx));
            if (pos > tasks.size() || pos < 1) {
                throw new TaskOutOfBoundsException();
            }
            ui.printTaskAfterDelete(tasks.get(pos));
            tasks.remove(pos);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (TaskOutOfBoundsException e) {
            ui.printTaskOutOfBoundsException();
        }
    }

}
