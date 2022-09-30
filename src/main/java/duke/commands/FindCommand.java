package duke.commands;

import duke.common.ErrorMessages;
import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.data.exception.FindNoKeywordsException;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents a find command object that will execute the operations for Find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";
    protected static final String EMPTY_STRING = "";
    protected String keywords;

    /**
     * Initialises the variables of the FindCommand class.
     *
     * @param keywords A string containing the keywords used in the search expression.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks the format of find to ensure that it contains keywords used in the search expression.
     *
     * @param keywords A string containing the keywords used in the search expression.
     * @throws FindNoKeywordsException If a user does not enter a search expression for Find.
     */
    public void checkFindFormat(String keywords) throws FindNoKeywordsException {
        if (keywords.isBlank()) {
            throw new FindNoKeywordsException();
        }
    }

    /**
     * Executes the operations related to the command.
     *
     * @param ui An instance of the TextUi class.
     * @param tasks An instance of the TaskList class.
     * @param storage An instance of the Storage class.
     */
    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
            // Checks the format of find to ensure that it contains keywords used in the search expression
            checkFindFormat(keywords);
            String tasksList = tasks.findTasks(keywords);
            if (tasksList.equals(EMPTY_STRING)) {
                ui.showCustomText(InfoMessages.MESSAGE_INFO_LIST_UNFILTERED);
                return;
            }
            ui.showTaskList(tasksList, InfoMessages.MESSAGE_INFO_LIST_FILTERED);
        } catch (FindNoKeywordsException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_FIND_FORMAT);
        }
    }
}
