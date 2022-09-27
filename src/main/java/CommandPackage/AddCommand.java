package CommandPackage;

import ExceptionsPackage.DukeException;
import TaskPackage.TaskList;
import TaskPackage.Task;
import TaskPackage.Deadline;
import TaskPackage.Event;
import TaskPackage.Todo;
import UiPackage.Ui;
import StoragePackage.Storage;

public class AddCommand extends Command {
    protected String type, description, time;
    public AddCommand(String type, String description, String time){
        this.type = type;
        this.description = description;
        this.time = time;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Task("");
        switch(type){
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                task = new Deadline(description,time);
                break;
            case "event":
                task = new Event(description,time);
                break;
        }
        tasks.getTasks().add(task);
        ui.printNumberOfTasksMessage(tasks.getTasks().size());
        storage.saveTasks(tasks);

    }
}
