package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String input;

    /**
     * Constructor of FindCommand Class
     *
     * @param input Input from User
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Find tasks from Tasks that match the User Input
     *
     * @param tasks TaskList of tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     * @throws DukeEmptyDescriptionException Exception when userInput has no searchPhrase
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException {
        tasks.findTask(input,ui);
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
