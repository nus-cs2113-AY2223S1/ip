package CommandPackage;

import ExceptionsPackage.*;
import TaskPackage.TaskList;
import TaskPackage.Task;
import TaskPackage.Deadline;
import TaskPackage.Event;
import TaskPackage.Todo;
import UiPackage.Ui;
import StoragePackage.Storage;

/*
Command which marks Task as done at given index.
 */
public class MarkCommand extends Command {
    protected int index;
    public MarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            tasks.getTasks().get(index - 1).markAsDone();
            System.out.println(
                    "Nice! I've marked this task as done:\n" +
                    tasks.getTasks().get(index - 1).toString() );
            storage.saveTasks(tasks);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Task index is out of bounds");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
