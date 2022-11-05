package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is a subclass of {@code Command} that provides an implementation for finding all {@code Task}s in the list of {@code Task}s that
 * match a given keyword.
 *
 * @author Dhanish.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor that takes in a keyword and initialises the {@code FindCommand} object accordingly.
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Method that finds all {@code Task}s that match with the keyword provided during initialisation and presents them for the user's purview.
     *
     * @param tasks   - list of {@code Task}s currently in the system
     * @param ui      - an object that deals with user input and interaction
     * @param storage - an object that contains the most up-to-date data of the tasks and offers methods for reading and re-writing file data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        tasks.find(keyword.toLowerCase());
        ui.printLine();
    }
}
