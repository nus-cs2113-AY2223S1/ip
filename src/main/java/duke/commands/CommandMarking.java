package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of a marking command after it has been recognised by the parser.
 */
public class CommandMarking {
    /**
     * Checks if indicted mark or unmark index is valid, then marks the task accordingly.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @param toMark A boolean such that true means to mark and false means to unmark.
     * @return Successful mark or unmark message to the user.
     * @throws DukeException.IllegalNoMarkIndexException If no valid integer index is indicated in the command.
     */
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
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
