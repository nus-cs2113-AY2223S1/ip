package duke.commands;

import duke.DukeException;
import duke.TaskManager;

public class CommandDeadline {
    public static void processNewDeadline(String command, TaskManager taskManager)
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
        taskManager.addNewDeadline(taskName, by, true);
    }
}
