package duke.commands;

import duke.common.ErrorMessages;
import duke.common.InfoMessages;
import duke.data.TaskList;
import duke.data.exception.FindNoKeywordsException;
import duke.storage.Storage;
import duke.ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND = "find";
    protected static final String EMPTY_STRING = "";
    protected String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    public void checkFindFormat(String keywords) throws FindNoKeywordsException {
        if (keywords.isBlank()) {
            throw new FindNoKeywordsException();
        }
    }

    @Override
    public void execute(TextUi ui, TaskList tasks, Storage storage) {
        try {
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
