package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a subclass of {@Command} that provides an implementation of listing all the current {@code Task}s.
 *
 * @author Dhanish
 */
public class ListCommand extends Command {

    /**
     * Constructor that initialises the {@code ListCommand} object by setting the value of {@code isExit} to be false.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * This method uses the {@code TaskList} object to list all the current {@code Task}s.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.listTasks();
        ui.printLine();
    }
}
