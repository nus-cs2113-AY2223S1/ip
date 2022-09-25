package duke.taskmanager.commands;

import duke.UI;
import duke.taskmanager.Storage;
import duke.taskmanager.TaskList;

public class EmptyCommand extends Command{
    private String keyword;
    public EmptyCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printEmptyException(keyword);
    }
}
