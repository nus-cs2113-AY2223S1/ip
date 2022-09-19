package duke.task;

import duke.exception.DukeException;


import java.util.ArrayList;

/**
 * Represents tasks recognised by Duke
 */
public abstract class Task {

    private String name;
    private boolean isDone;

    /**
     * Constructs a task object after extracting task name from user input
     * Task is set to not done by default
     * @param arguments user input containing task name (and task date & time)
     * @throws DukeException if task name is empty
     */
    public Task(String arguments) throws DukeException {
        setName(TaskNameParser.extractTaskName(arguments));
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
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract char taskType();

    /**
     * Returns icon based on isDone status of task
     * @return X if task is done, whitespace otherwise
     */
    public char doneIcon() {
        if (this.isDone) {
            return 'X';
        }
        return ' ';
    }

    /**
     * Lists the task for printing purposes
     * This method is overridden for task types with date & time
     * @return string containing task number, task type, task done icon, task name, task date & time if any
     */
    public String listTask(ArrayList<Task> tasks) {
        return String.format("%d.[%c][%c] %s",
                tasks.indexOf(this) + 1, taskType(), doneIcon(), getName());
    }

}
