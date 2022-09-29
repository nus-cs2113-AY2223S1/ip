package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super(input);
        //TODO Auto-generated constructor stub
    }

    public static final String KEYWORD = "mark";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        TaskList.markTask(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
}

