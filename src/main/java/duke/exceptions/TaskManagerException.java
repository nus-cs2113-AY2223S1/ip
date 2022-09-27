package duke.exceptions;

/**
 * Define exceptions thrown by {@link duke.data.task.TaskManager#}.
 */
@SuppressWarnings("unused")
public class TaskManagerException {
    /**
     * Thrown when a task cannot be found in {@link duke.data.task.TaskManager#}.
     */
    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException() {
        }

        public TaskNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when a task cannot be saved by {@link duke.data.task.TaskManager#}.
     */
    public static class TasksFileIOException extends DukeException {
        public TasksFileIOException() {
        }

        public TasksFileIOException(String message) {
            super(message);
        }
    }
}
