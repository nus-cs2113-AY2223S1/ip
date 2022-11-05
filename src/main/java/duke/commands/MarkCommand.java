package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a subclass of {@code Command} that provides an implementation for marking a specific {@code Task} as done
 * in the list of {@code Task}s.
 *
 * @author Dhanish
 */
public class MarkCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor that takes in the number of the {@code Task} to be marked as done
     * and instantiates the {@code MarkCommand} object accordingly.
     *
     * @param taskNumber - the number of the {@code Task} to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * This method marks the {@code Task} corresponding to the input number as done, if possible, and saves the data accordingly.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.markTaskAsDone(taskNumber);
        storage.saveData(tasks);
        ui.printLine();
    }
}
