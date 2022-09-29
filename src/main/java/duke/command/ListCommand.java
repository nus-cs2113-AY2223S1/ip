package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
        //TODO Auto-generated constructor stub
    }

    public static final String KEYWORD = "list";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) {
        Ui.printTaskList(tasks);
        return tasks;
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
}

