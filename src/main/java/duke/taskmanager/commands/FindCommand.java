package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * Searches for <code>tasks</code> base on keyword.
 */
public class FindCommand extends Command{
    public FindCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Finds all the tasks in the <code>TaskList</code> that contains the user specified keyword(s).
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int DescStartIdx = userInput.indexOf(' ') + 1;
        String keywords = userInput.substring(DescStartIdx);
        ui.printMatchList(tasks, keywords);
    }
}
