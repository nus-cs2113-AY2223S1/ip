package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeDukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class TaskDateTimeParser {

    public static String extractTaskDateTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new InvalidDateTimeDukeException();
        }
        String taskDateTime = arguments.substring(arguments.indexOf('/') + 1).trim();
        if (taskDateTime.length() == 0) {
            throw new InvalidDateTimeDukeException();
        }
        return taskDateTime;
    }

    public static LocalDate extractTaskDate(String taskDateTime) throws DukeException {
        String taskDate = taskDateTime;
        if (taskDateTime.contains(" ")) {
            taskDate = taskDateTime.substring(0, taskDateTime.indexOf(' '));
        }
        try {
            return LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeDukeException();
        }
    }

    public static LocalTime extractTaskTime(String taskDateTime) throws DukeException {
        if (!taskDateTime.contains(" ")) {
            return null;
        }
        String taskTime = taskDateTime.substring(taskDateTime.indexOf(' ') + 1).trim();
        if (taskTime.length() > 0) {
            try {
                return LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeDukeException();
            }
        }
        return null;
    }

    public static String getDateTime(LocalDate date, LocalTime time) {
        String taskDateTime = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (time != null) {
            taskDateTime += ' ' + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return taskDateTime;
    }

    public static String listDateTime(LocalDate date, LocalTime time) {
        String taskDateTime = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        if (time != null) {
            taskDateTime += ' ' + time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return taskDateTime;
    }

}
