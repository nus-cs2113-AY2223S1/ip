package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandFind {
    private static final String END_OF_LINE = "____________________";
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
