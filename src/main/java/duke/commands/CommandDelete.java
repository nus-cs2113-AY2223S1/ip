package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of a delete command after it has been recognised by the parser.
 */
public class CommandDelete {
    /**
     * Checks the targeted index to be deleted, then delete the corresponding task in the task list.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return Successful or failed deletion message to user.
     * @throws DukeException.IllegalDeleteIndexException If index is out of range or not an integer.
     */
    public static String processDelete(String command, TaskList taskList)
            throws DukeException.IllegalDeleteIndexException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length != 2 || !isInteger(arrOfCommand[1])) {
            throw new DukeException.IllegalDeleteIndexException();
        }

        try {
            return taskList.deleteTask(Integer.parseInt(arrOfCommand[1]));
        } catch (DukeException.IllegalDeleteTargetException e) {
            return e.getMessage();
        }
    }

    private static boolean isInteger(String toCheck) {
        try {
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
