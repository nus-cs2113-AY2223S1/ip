package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class CommandEvent extends Command {
    public static String processNewEvent(String command, TaskList taskList)
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

        try {
            checkDateTime(at);
        } catch (DukeException.IllegalDateTimeException e) {
            throw new DukeException.IllegalEventDateException();
        }

        return taskList.addNewEvent(taskName, at, true);
    }
}
