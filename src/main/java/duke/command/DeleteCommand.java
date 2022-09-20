package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        tasks.deleteTask(input,ui);
        storage.writeToFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
