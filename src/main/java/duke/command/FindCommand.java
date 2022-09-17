package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * A representation of a command to find all matching tasks with a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword used to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes find command.
     *
     * @param taskList The list of tasks to work with.
     * @param ui The Duke user interface for user to interact with.
     */
    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        TaskList result = taskList.findTasksByKeyword(keyword);
        ui.printResultList(result);
    }
}
