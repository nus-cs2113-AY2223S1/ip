package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeIOException;
import duke.task.TaskList;

public class MarkCommand extends Command{
    private String input;

    /**
     * Constructor of MarkCommand Class
     *
     * @param input
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a Task from Tasks and saves Tasks to Storage
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeIOException Exception when writeToFile has exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        tasks.markTask(input, ui);
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
