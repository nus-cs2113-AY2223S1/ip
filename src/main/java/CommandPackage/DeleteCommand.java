package CommandPackage;

import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import TaskPackage.Deadline;
import TaskPackage.Event;
import TaskPackage.Todo;
import UiPackage.Ui;
import StoragePackage.Storage;

/*
A Command which deletes Task at a given index
 */
public class DeleteCommand extends Command {
    protected int index;
    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            System.out.println(
                    "Noted. I've removed this task:\n" +
                            tasks.getTasks().get(index - 1).toString()
            );
            tasks.getTasks().remove(index - 1);
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
