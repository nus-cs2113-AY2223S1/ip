package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionType;

public abstract class Task {

    private String name;
    private boolean isDone;

    public Task(String arguments) throws DukeException {
        if (arguments.length() == 0 || arguments.indexOf('/') == 0) {
            throw new DukeException(ExceptionType.MISSING_DESCRIPTION);
        }
        if (arguments.contains("/")) {
            arguments = arguments.substring(0, arguments.indexOf('/'));
        }
        setName(arguments.trim());
        this.isDone = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markAsDone() {
        this.isDone = true;
        listTask();
    }

    public void markAsNotDone() {
        this.isDone = false;
        listTask();
    }

    public char taskType() {
        return '-';
    }

    public char doneIcon() {
        if (this.isDone) {
            return 'X';
        }
        return ' ';
    }

    public String taskDescription() {
        return this.name;
    }

    public String listTask() {
        return String.format("%d.[%c][%c] %s",
                TaskManager.Tasks.indexOf(this) + 1, taskType(), doneIcon(), taskDescription());
    }

    public static String extractTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new DukeException(ExceptionType.MISSING_TIME);
        }
        return arguments.substring(arguments.indexOf('/') + 1);
    }

}
