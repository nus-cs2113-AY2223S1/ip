package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeDukeException;
import duke.exception.MissingDescriptionDukeException;

/**
 * Represents task
 */
public abstract class Task {

    private String name;
    private boolean isDone;

    /**
     * Constructs a task object after extracting task name from user input
     * Task is set to not done by default
     * @param arguments user input containing task name (and task date & time)
     * @throws DukeException if task name is invalid
     */
    public Task(String arguments) throws DukeException {
        TaskNameParser.checkForTaskName(arguments);
        setName(TaskNameParser.extractTaskName(arguments));
        this.isDone = false;
    }


    /**
     * Checks that user input contains a task name, ignoring task date & time if any
     * @param arguments user input containing task name (and task date & time)
     * @throws DukeException if task name is empty
     */
    private static void checkForTaskName(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new MissingDescriptionDukeException();
        }
    }

    /**
     * Extracts task name from user input, ignoring task date & time if any
     * @param arguments user input containing task name (and task date & time)
     * @return task name
     */
    private static String extractTaskName(String arguments) {
        String taskName = arguments;
        if (taskName.contains("/")) {
            taskName = taskName.substring(0, arguments.indexOf('/'));
        }
        return taskName.trim();
    }

    /**
     * Extracts task date & time from user input, ignoring task name
     * @param arguments user input containing task name (and task date & time)
     * @return task date & time
     * @throws DukeException if task date & time is invalid
     */
    protected static String extractTaskTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new InvalidDateTimeDukeException();
        }
        String taskTime = arguments.substring(arguments.indexOf('/') + 1).trim();
        if (taskTime.length() == 0) {
            throw new InvalidDateTimeDukeException();
        }
        return taskTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract char taskType();

    /**
     * Returns icon based on done status of task
     * @return X if task is done, space otherwise
     */
    public char doneIcon() {
        if (this.isDone) {
            return 'X';
        }
        return ' ';
    }

    /**
     * Lists the task for printing purposes
     * @return string containing task number, task type, task done icon, task name, task date & time if any
     */
    public String listTask() {
        return String.format("%d.[%c][%c] %s",
                TaskList.Tasks.indexOf(this) + 1, taskType(), doneIcon(), getName());
    }

}
