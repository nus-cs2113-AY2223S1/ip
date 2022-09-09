package duke.commands;

import duke.DukeException;
import duke.TaskManager;

public class CommandDelete {
    public static void processDelete(String command, TaskManager taskManager)
            throws DukeException.IllegalDeleteIndexException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length != 2 || !isInteger(arrOfCommand[1])) {
            throw new DukeException.IllegalDeleteIndexException();
        }

        try {
            taskManager.deleteTask(Integer.parseInt(arrOfCommand[1]));
        } catch (DukeException.IllegalDeleteTargetException e) {
            System.out.println("Index of task is out of range");
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
