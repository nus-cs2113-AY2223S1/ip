package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandDelete {
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
            int intValue = Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
