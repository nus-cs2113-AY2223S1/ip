package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "delete";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        TaskList.deleteTask(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
