package duke.task;

import duke.DukeException;

/**
 * Define exceptions thrown by {@link TaskManager#}.
 */
public class TaskManagerException {
    /**
     * Thrown when a task cannot be found in {@link TaskManager#}.
     */
    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException() {
        }

        public TaskNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when a task cannot be saved by {@link TaskManager#}.
     */
    public static class TasksFileIOException extends DukeException {
        public TasksFileIOException() {
        }

        public TasksFileIOException(String message) {
            super(message);
        }
    }
}
