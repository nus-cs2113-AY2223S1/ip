package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNumberExceedException;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        boolean isEmpty = commands.length == 1;
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            try {
                int markNumber = Integer.parseInt(commands[1]);
                TaskList.unmarkTask(markNumber);
            } catch (TaskNumberExceedException e) {
                System.out.println("The digit you entered is beyond the number of task. :/");
            } catch (Exception e) {
                System.out.println("Please enter only digits for the parameters. :/");
            }
        }
    }
}
