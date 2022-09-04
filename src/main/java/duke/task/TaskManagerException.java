package duke.task;

import duke.DukeException;

public class TaskManagerException {
    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException() {
        }

        public TaskNotFoundException(String message) {
            super(message);
        }
    }
}
