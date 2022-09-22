package duke.task;

import duke.exception.DukeException;
import duke.exception.MissingTaskNameDukeException;

/**
 * Converts user input into task name
 */
public class TaskNameParser {

    /**
     * Checks for and extracts task name from user input, ignoring task date & time if any
     *
     * @param arguments user input containing task name (and task date & time)
     * @return task name
     * @throws DukeException if task name is empty
     */
    public static String extractTaskName(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new MissingTaskNameDukeException();
        }
        String taskName = arguments;
        if (taskName.contains("/")) {
            taskName = taskName.substring(0, arguments.indexOf('/'));
        }
        return taskName.trim();
    }
}
