package duke.task;

import duke.exception.DukeException;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;


public class Deadline extends Task {

    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String arguments) throws DukeException {
        super(arguments);
        String dateTime = TaskDateTimeParser.extractTaskDateTime(arguments);
        this.date = TaskDateTimeParser.extractTaskDate(dateTime);
        this.time = TaskDateTimeParser.extractTaskTime(dateTime);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateTime() {
        return TaskDateTimeParser.getDateTime(date, time);
    }

    private String listDateTime() {
        return TaskDateTimeParser.listDateTime(date, time);
    }

    @Override
    public char taskType() {
        return 'D';
    }

    @Override
    public String listTask(ArrayList<Task> tasks) {
        return String.format("%s (%s)", super.listTask(tasks), listDateTime());
    }
}
