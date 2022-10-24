package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidEventInputException;
import duke.task.TaskList;

public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws InvalidEventInputException {
        TaskList.findMatchingTasks(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
    