package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandMarking {
    public static String processMarking(String command, TaskList taskList, boolean toMark)
            throws DukeException.IllegalNoMarkIndexException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length != 2 || !isInteger(arrOfCommand[1])) {
            throw new DukeException.IllegalNoMarkIndexException();
        }

        try {
            return taskList.markTasks(toMark, Integer.parseInt(arrOfCommand[1]), true);
        } catch (DukeException.IllegalMarkTargetException e) {
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
