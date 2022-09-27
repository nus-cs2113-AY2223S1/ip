package duke.commands;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.list();
    }
}
