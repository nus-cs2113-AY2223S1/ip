package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.UI;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UI ui) throws DukeException {
        TaskList result = taskList.findTasksByKeyword(keyword);
        ui.printResultList(result);
    }
}
