package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a subclass of {@code Command} that prepares the program to exit.
 *
 * @author Dhanish
 */
public class ExitCommand extends Command {

    /**
     * Constructor that instantiates the {@code ExitCommand} by letting the value of {@code isExit} to be true.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * This method does nothing in particular as the exiting logic is handled elsewhere in the code.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
