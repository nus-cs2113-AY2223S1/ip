package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String KEYWORD = "list";
    
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) {
        Ui.printTaskList(tasks);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

