package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Represent a command with command word specified.
 * Used when command word is 'list' to list all the existing tasks in task list.
 */
public class ListCommand extends Command {

    public ListCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.list();
    }
}
