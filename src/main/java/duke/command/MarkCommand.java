package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNumberExceedException;


public class MarkCommand extends Command {

    /**
     * Initialises default state of mark command and execute the operations.
     * @param commands User input indicating the command and task number to mark.
     * @throws EmptyDescriptionException If commands length is 1.
     */
    public MarkCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

    /**
     * Marks the task that is indicated by the user.
     *
     * @param commands User input that contains command and task to be marked.
     * @throws EmptyDescriptionException If commands length is 1.
     */
    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        boolean isEmpty = commands.length == 1;
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            try {
                int markNumber = Integer.parseInt(commands[1]);
                TaskList.markTask(markNumber);
            } catch (TaskNumberExceedException e) {
                System.out.println("The digit you entered is beyond the number of task. :/");
            } catch (Exception e) {
                System.out.println("Please enter only digits for the parameters. :/");
            }


        }
    }
}
