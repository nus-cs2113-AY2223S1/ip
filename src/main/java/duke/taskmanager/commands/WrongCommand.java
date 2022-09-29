package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

/**
 * Unknown user input
 */
public class WrongCommand extends Command {
    public WrongCommand(String userInput, String firstWord) {
        super(userInput, firstWord);
    }

    /**
     * Prints wrong user input message
     *
     * @param tasks   stores all the user's current tasks
     * @param ui      contains the formatted outputs
     * @param storage stores the tasks after the programme closes
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printWrongCommandException();
    }
}
