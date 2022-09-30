package Commands;

import Misc.DukeException;
import Misc.Storage;
import Misc.Ui;
import Tasks.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(ArrayList<String> elements) {
        super(elements);
    }
    
    /**
     * Executes the command.
     * @param tasks Tasks list.
     * @param ui UI object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index;

        try{
            index = Integer.parseInt(elements.get(1)) - 1;
            if (index < 0 | index >= tasks.getTasks().size()) {
                throw new DukeException(6);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(6);
        }

        ui.showDeleteTaskNotification(tasks.getTasks(), index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
    }
}
