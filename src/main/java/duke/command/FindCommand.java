package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidEventInputException;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "find";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws InvalidEventInputException {
        // find matching tasks
        TaskList.findMatchingTasks(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
    