package duke.commands;

import duke.DukeException;
import duke.TaskManager;

public class CommandEvent {
    public static void processNewEvent(String command, TaskManager taskManager)
            throws DukeException.IllegalEventFormatException, DukeException.IllegalEventDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalEventFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalEventDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String at = command.substring(dividerPosition + 1);
        taskManager.addNewEvent(taskName, at, true);
    }
}
