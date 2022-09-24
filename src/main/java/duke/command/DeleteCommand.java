package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNumberExceedException;

public class DeleteCommand extends Command{

    /**
     * Initialises default state before executing delete operation.
     *
     * @param commands User inputted command that separates between command type and description
     * @throws EmptyDescriptionException If commands[1] is empty
     */
    public DeleteCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

    /**
     * Deletes the task that is selected by the user by indicating a digit.
     * If the digit is not indicated, EmptyDescriptionException will be thrown.
     *
     * @param commands User input that separates between delete command and a digit
     *                 that indicates the task to be deleted.
     * @throws EmptyDescriptionException If commands length is 1.
     */
    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        // If the length equals to 1, it only have command without description.
        boolean isEmpty = (commands.length == 1);
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            try {
                // Change the description into a integer to indicate the task to be deleted.
                int deleteNumber = Integer.parseInt(commands[1]);
                TaskList.deleteTask(deleteNumber);
            } catch (TaskNumberExceedException e) {
                System.out.println("The digit you entered is beyond the number of task. :/");
            } catch (Exception e) {
                System.out.println("Please enter only digits for the parameters. :/");
            }
        }
    }


}
