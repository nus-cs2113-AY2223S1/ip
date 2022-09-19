package duke.task;

import duke.exception.DukeException;
import duke.exception.MissingDescriptionDukeException;

public abstract class TaskNameParser {

    public static void checkForTaskName(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new MissingDescriptionDukeException();
        }
    }

    public static String extractTaskName(String arguments) {
        String taskName = arguments;
        if (taskName.contains("/")) {
            taskName = taskName.substring(0, arguments.indexOf('/'));
        }
        return taskName.trim();
    }
}
