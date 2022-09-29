package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class ByeCommand extends Command {
    public static final String KEYWORD = "bye";

    /**
     * Creates an exit command
     * 
     * @param input The user input string
     */
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        Ui.printByeMessage();
        return tasks;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
