package duke.command;

import duke.Ui;
import duke.exception.EmptyDescriptionException;

public class FindCommand extends Command {

    public FindCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

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
