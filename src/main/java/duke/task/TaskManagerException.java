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

    public static class TasksFileIOException extends DukeException {
        public TasksFileIOException() {
        }

        public TasksFileIOException(String message) {
            super(message);
        }
    }
}
