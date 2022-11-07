package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Represents the command to manage the processing of a find command after it has been recognised by the parser.
 */
public class CommandFind {
    private static final String END_OF_LINE = "____________________";

    /**
     * Checks the command for a keyword to search, then searches for it in the task list.
     *
     * @param command Command read from user.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     * @return A list of tasks with keyphrase if any exists.
     * @throws DukeException.IllegalFindCommandException If no valid substring is given.
     */
    public static String processFind(String command, TaskList taskList)
            throws DukeException.IllegalFindCommandException {
        int spacePosition = command.trim().indexOf(" ");
        if (spacePosition < 0) {
            throw new DukeException.IllegalFindCommandException();
        }
        String keyphrase = command.substring(spacePosition + 1);
        return taskList.findTasksWithKeyphrase(keyphrase) + END_OF_LINE;
    }
}
