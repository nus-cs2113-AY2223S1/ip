package duke.task;

import duke.exception.DukeException;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Represents task of type event
 */
public class Event extends Task {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs an Event object
     * Sets event date & time after extracting from user input
     * @param arguments user input containing task name and task date & time
     * @throws DukeException if task name or task date & time are invalid
     */
    public Event(String arguments) throws DukeException {
        super(arguments);
        String dateTime = TaskDateTimeParser.extractTaskDateTime(arguments);
        this.date = TaskDateTimeParser.extractTaskDate(dateTime);
        this.time = TaskDateTimeParser.extractTaskTime(dateTime);
    }

    public LocalDate getDate() {
        return date;
    }

    public String saveDateTime() {
        return TaskDateTimeParser.saveDateTime(date, time);
    }

    private String listDateTime() {
        return TaskDateTimeParser.listDateTime(date, time);
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String listTask(ArrayList<Task> tasks) {
        return String.format("%s (%s)", super.listTask(tasks), listDateTime());
    }
}
