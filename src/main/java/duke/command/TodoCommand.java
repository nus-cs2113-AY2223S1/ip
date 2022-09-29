package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String input) {
        super(input);
        //TODO Auto-generated constructor stub
    }

    public static final String KEYWORD = "todo";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        TaskList.addTodo(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }
}
