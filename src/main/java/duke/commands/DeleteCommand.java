package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a subclass of {@code Command} that provides an implementation for deleting a {@code Task} from the list of {@code Task}s.
 *
 * @author Dhanish
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor that takes in the number of the {@code Task} to be removed from the list of {@code Task}s
     * and instantiates the {@code Command} accordingly.
     *
     * @param taskNumber - number of the {@code Task} to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * This method deletes the {@code Task} corresponding to the input task number (if possible) from the list of {@code Task}s, and saves the data accordingly.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.removeTask(taskNumber);
        storage.saveData(tasks);
        ui.printLine();
    }
}
