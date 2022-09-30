package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "list";

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

