package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class Deadline extends Task {

    private String deadlineTime;

    public Deadline(String arguments) throws DukeException {
        super(arguments);
        this.deadlineTime = extractTaskTime(arguments);
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    @Override
    public String listTask(ArrayList<Task> tasks) {
        return String.format("%s (%s)", super.listTask(tasks), this.deadlineTime);
    }
}
