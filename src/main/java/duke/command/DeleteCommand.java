package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor of DeleteCommand Class
     *
     * @param input Input from User
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Delete a Task from Tasks and saves Tasks to Storage
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeIOException Exception when writeToFile has exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        tasks.deleteTask(input,ui);
        storage.writeToFile(tasks);
    }

    /**
     * Returns false as command is not ByeCommand
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
