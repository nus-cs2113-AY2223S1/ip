package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandDeadline {
    public static String processNewDeadline(String command, TaskList taskList)
            throws DukeException.IllegalDeadlineFormatException, DukeException.IllegalDeadlineDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalDeadlineFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalDeadlineDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String by = command.substring(dividerPosition + 1);
        return taskList.addNewDeadline(taskName, by, true);
    }
}
