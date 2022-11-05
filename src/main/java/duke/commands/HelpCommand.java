package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a simple subclass of {@code Command} that assists in the displaying of the various commands to the user.
 *
 * @author Dhanish
 */
public class HelpCommand extends Command {

    /**
     * Constructor that instantiates the {@code HelpCommand} by letting the value of {@code isExit} to be false.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * This method uses the {@code Ui} object to print a help message.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        ui.printHelpMessage();
        ui.printLine();
    }
}
