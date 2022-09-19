package duke.task;

import duke.exception.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Task {

    private String name;
    private boolean isDone;

    public Task(String arguments) throws DukeException {
        TaskNameParser.checkForTaskName(arguments);
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
