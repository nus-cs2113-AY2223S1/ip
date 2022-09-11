package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionType;

public class Deadline extends Task {

    private String deadlineTime;

    public Deadline(String arguments) throws DukeException {
        super(arguments);
        String deadlineTime = extractTime(arguments);
        if (deadlineTime.length() == 0) {
            throw new DukeException(ExceptionType.MISSING_TIME);
        }
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    @Override
    public String taskDescription() {
        return String.format("%s (%s)", this.getName(), this.deadlineTime);
    }
}
