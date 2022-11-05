package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * This class is a subclass of {@code Command} that provides an implementation for adding {@code Task}s into the current list of {@code Task}s.
 *
 * @author Dhanish
 */
public class AddCommand extends Command {

    private final Task taskToBeAdded;

    /**
     * Constructor that takes in the {@code Task} to be added and instantiates the {@code AddCommand} accordingly.
     *
     * @param taskToBeAdded - the {@code Task} to be added to the list of {@code Task}s.
     */
    public AddCommand(Task taskToBeAdded) {
        super(false);
        this.taskToBeAdded = taskToBeAdded;
    }

    /**
     * This method adds the {@code Task} that it was instantiated with into the list of {@code Task}s, and saves the data accordingly.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.addTask(taskToBeAdded);
        storage.saveData(tasks);
        ui.printLine();
    }
}
