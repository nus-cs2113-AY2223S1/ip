package CommandPackage;

import ExceptionsPackage.DukeException;
import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import UiPackage.Ui;
import StoragePackage.Storage;
/*
Template class which represents any given command
 */
public abstract class Command {
    /*
    Returns Boolean indicating if Weng should exit.
     */
    public abstract boolean isExit();
    /*
    Template for executing a given command, which takes in TaskList, Storage and Ui as parameters
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;


}
//