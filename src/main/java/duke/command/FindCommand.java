package duke.command;

import duke.Ui;
import duke.exception.EmptyDescriptionException;

public class FindCommand extends Command {

    /**
     * Initialises the default state of find command then execute the command.
     *
     * @param commands User input that split into command type and matching string
     *                 to find.
     * @throws EmptyDescriptionException If commands length is 1.
     */
    public FindCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

    /**
     * Searches for matching string in list of task.
     *
     * @param commands User input that split into command type and matching string
     *                 to find.
     * @throws EmptyDescriptionException If commands length is 1.
     */
    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        boolean isEmpty = commands.length == 1;
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            String matchingTask = commands[1];
            Ui.findMatchingTasks(matchingTask);
        }
    }
}
