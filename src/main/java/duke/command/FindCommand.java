package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException, EmptyDescriptionException {
        tasks.findTask(input,ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
