package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class Event extends Task {

    private String eventTime;

    public Event(String arguments) throws DukeException {
        super(arguments);
        this.eventTime = extractTaskTime(arguments);
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String listTask(ArrayList<Task> tasks) {
        return String.format("%s (%s)", super.listTask(tasks), this.eventTime);
    }
}
