package duke.task;

import duke.exception.DukeException;
import duke.exception.MissingDescriptionDukeException;
import duke.exception.MissingTimeDukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Task {

    private String name;
    private boolean isDone;

    public Task(String arguments) throws DukeException {
        checkForTaskName(arguments);
        setName(extractTaskName(arguments));
        this.isDone = false;
    }

    private static void checkForTaskName(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new MissingDescriptionDukeException();
        }
    }

    private static String extractTaskName(String arguments) {
        String taskName = arguments;
        if (taskName.contains("/")) {
            taskName = taskName.substring(0, arguments.indexOf('/'));
        }
        return taskName.trim();
    }

    protected static String extractTaskTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new MissingTimeDukeException();
        }
        String taskTime = arguments.substring(arguments.indexOf('/') + 1).trim();
        if (taskTime.length() == 0) {
            throw new MissingTimeDukeException();
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

    public char doneIcon() {
        if (this.isDone) {
            return 'X';
        }
        return ' ';
    }

    public String listTask(ArrayList<Task> tasks) {
        return String.format("%d.[%c][%c] %s",
                tasks.indexOf(this) + 1, taskType(), doneIcon(), getName());
    }

}
