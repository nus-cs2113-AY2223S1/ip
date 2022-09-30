package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Provides the management of a deadline task with getters and setters.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private static final String TIME_PREFIX = " (by: ";
    private static final String TIME_POSTFIX = ")";
    private static final String TIME_INFIX = ", ";
    private static final String EMPTY_SPACE = " ";
    private static final String DELIMITER = "/";
    private static LocalDate dueByDate;
    private static LocalTime dueByTime;
    public static String dueBy;

    /**
     * Instantiates a new deadline task when user initialises a new instance of this class.
     *
     * @param title A string that represents the title of the task.
     * @param dueBy A string that represents the deadline of the task.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public Deadline(String title, String dueBy, boolean isDone) throws DateTimeParseException {
        super(title, isDone);
        // Stores the deadline of task as LocalDate and LocalTime objects in the program.
        ConvertStringToDatetime(dueBy);
        this.dueBy = dueBy;
    }

    /**
     * Stores the deadline of task as LocalDate and LocalTime objects in the program.
     *
     * @param dueBy A string that represents the deadline of the task.
     * @throws DateTimeParseException If an error is caught in converting the string to datetime format.
     */
    public static void ConvertStringToDatetime(String dueBy) throws DateTimeParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        // Splits the deadline into two parts, i.e. date and/or time, whichever applicable
        if (dueBy.contains(EMPTY_SPACE)) {
            String[] dueBySplits = dueBy.split(EMPTY_SPACE);
            dueByDate = LocalDate.parse(dueBySplits[0], dateFormatter);
            dueByTime = LocalTime.parse(dueBySplits[1], timeFormatter);
        } else if (dueBy.contains(DELIMITER)) {
            dueByDate = LocalDate.parse(dueBy, dateFormatter);
        } else {
            dueByTime = LocalTime.parse(dueBy, timeFormatter);
        }
    }

    /**
     * Converts the LocalDate and LocalTime objects into a meaningful deadline format.
     *
     * @param dueByDate A LocalDate object that represents the date portion of the task deadline.
     * @param dueByTime A LocalTime object that represents the time portion of the task deadline.
     * @return A string that represents the deadline of the task in a meaningful format.
     */
    public String ConvertDatetimeToString(LocalDate dueByDate, LocalTime dueByTime) {
        DateTimeFormatter datePrintFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy (E)");
        DateTimeFormatter timePrintFormatter = DateTimeFormatter.ofPattern("h:mm a");

        String dueBy;
        // Joins the two parts, i.e. date and/or time into a single deadline, whichever applicable
        if (dueByDate != null && dueByTime != null) {
            dueBy = dueByDate.format(datePrintFormatter) + TIME_INFIX +
                    dueByTime.format(timePrintFormatter);
        } else if (dueByTime != null) {
            dueBy = dueByTime.format(timePrintFormatter);
        } else {
            dueBy = dueByDate.format(datePrintFormatter);
        }
        return dueBy;
    }

    /**
     * Gets the deadline of a deadline task.
     *
     * @return A string that represents the deadline of the task.
     */
    public String getDueBy() {
        return dueBy;
    }

    /**
     * Gets the completion status, title and deadline of a deadline task.
     *
     * @return A string containing the information for a deadline task.
     */
    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails() + TIME_PREFIX +
                ConvertDatetimeToString(dueByDate, dueByTime) + TIME_POSTFIX;
    }
}
