package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDateTimeDukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts user input into task date & time
 * Date is mandatory but time is optional
 */
public abstract class TaskDateTimeParser {

    /**
     * Extracts task date & time from user input, ignoring task name
     * @param arguments user input containing task name (and task date & time)
     * @return task date & time as string
     * @throws DukeException if task date & time is empty
     */
    public static String extractTaskDateTime(String arguments) throws DukeException {
        if (arguments.indexOf('/') == -1) {
            throw new InvalidTaskDateTimeDukeException();
        }
        String taskDateTime = arguments.substring(arguments.indexOf('/') + 1).trim();
        if (taskDateTime.length() == 0) {
            throw new InvalidTaskDateTimeDukeException();
        }
        return taskDateTime;
    }

    /**
     * Extracts task date from task date & time
     * @param taskDateTime task date & time as string
     * @return task date as LocalDate
     * @throws DukeException if task date is invalid
     */
    public static LocalDate extractTaskDate(String taskDateTime) throws DukeException {
        String taskDate = taskDateTime;
        if (taskDateTime.contains(" ")) {
            taskDate = taskDateTime.substring(0, taskDateTime.indexOf(' '));
        }
        try {
            return LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeDukeException();
        }
    }

    /**
     * Extracts task time from task date & time, if any
     * @param taskDateTime task date & time as string
     * @return task time as LocalTime
     * @throws DukeException if task time is invalid
     */
    public static LocalTime extractTaskTime(String taskDateTime) throws DukeException {
        if (!taskDateTime.contains(" ")) {
            return null;
        }
        String taskTime = taskDateTime.substring(taskDateTime.indexOf(' ') + 1).trim();
        if (taskTime.length() > 0) {
            try {
                return LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                throw new InvalidTaskDateTimeDukeException();
            }
        }
        return null;
    }

    /**
     * Converts task date & time for saving purposes
     * @param date task date
     * @param time task time
     * @return task date & time as string
     */
    public static String saveDateTime(LocalDate date, LocalTime time) {
        String taskDateTime = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (time != null) {
            taskDateTime += ' ' + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return taskDateTime;
    }

    /**
     * Converts task date & time for printing purposes
     * @param date task date
     * @param time task time
     * @return task date & time as string
     */
    public static String listDateTime(LocalDate date, LocalTime time) {
        String taskDateTime = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        if (time != null) {
            taskDateTime += ' ' + time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return taskDateTime;
    }

}
