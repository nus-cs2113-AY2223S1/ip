package duke.taskmanager.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A <code>Todo task</code> that only has <code>description</code> and <code>isDone</code>.
 * Does not have an associated <code>date</code> and <code>time</code>.
 */
public class Todo extends Task {
    protected LocalDate date;
    protected String time;

    /**
     * Parses the command base on type of task to set description.
     *
     * @param command  user input in the form of "TASK_TYPE description ADDITIONAL_INFORMATION_FOR_DEADLINE_AND_EVENT"
     * @param lastChar ' ' represents a <code>todo</code> command while
     *                 '/' represents a <code>event</code> or <code>deadline</code> command
     */
    public Todo(String command, Character lastChar) {
        int DescStartIdx = command.indexOf(' ') + 1;
        String description;
        if (lastChar.equals(' ')) {
            description = command.substring(DescStartIdx);
        } else {
            int DescEndIdx = command.indexOf('/');
            description = command.substring(DescStartIdx, DescEndIdx);
        }
        setDescription(description);
    }

    /**
     * Separates a date and time <code>String</code> of a specific format into a <code>date</code> date
     * and a <code>String</code> time. If the <code>String</code> is not of the specified
     * format, the date and time will be set to a week from current time.
     *
     * @param dateAndTime date and time of format "yyyy-MM-dd HHmm'
     */
    public void findDateAndTime(String dateAndTime) {
        try {
            Date dateTime = new SimpleDateFormat("yyyy-MM-dd HHmm").parse(dateAndTime);
            date = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dateTime));
            time = new SimpleDateFormat("h:mm aa").format(dateTime);
        } catch (ParseException e) {
            System.out.println("Sorry. Date and Time cannot be parsed");
            date = LocalDate.now().plusWeeks(1);
            time = new SimpleDateFormat("h:mm aa").format(new Date());
        }
    }

    /**
     * Format the date to a specified format.
     *
     * @return formatted date
     */
    public String formatDate() {
        if (date != null){
            return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "";
        }

    }

    /**
     * <code>Todo</code> is associated with '<code>T</code>'.
     *
     * @return the formatted type
     */
    @Override
    protected String getTypeIcon() {
        return "[T]";
    }
}
