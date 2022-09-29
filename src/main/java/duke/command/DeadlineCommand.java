package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input) {
        super(input);
        //TODO Auto-generated constructor stub
    }

    public static final String KEYWORD = "deadline";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        TaskList.addDeadline(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
}
