package duke.commands;

import duke.DukeException;
import duke.TaskManager;

public class CommandMarking {
    public static void processMarking(String command, TaskManager taskManager, boolean toMark)
            throws DukeException.IllegalNoMarkIndexException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length <= 1 || !isInteger(arrOfCommand[1])) {
            throw new DukeException.IllegalNoMarkIndexException();
        }

        try {
            taskManager.markTasks(toMark, Integer.parseInt(arrOfCommand[1]), true);
        } catch (DukeException.IllegalMarkTargetException e) {
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
