package CommandPackage;

import CommandPackage.Command;
import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import UiPackage.Ui;
import StoragePackage.Storage;

import java.util.ArrayList;
/*
A Command which prints all related Tasks for a given keyword.
 */
public class FindCommand extends Command {
    protected String keyword;
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMatched(tasks,keyword);

    }
}

