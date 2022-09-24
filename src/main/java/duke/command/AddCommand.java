package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class AddCommand extends Command {

    /**
     * Initialises default state and execute add command.
     *
     * @param commands User input that is split by command and description.
     * @throws EmptyDescriptionException If commands[1] is empty.
     */
    public AddCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }


    /**
     * Executes the adding operation by attempting to format the date.
     * If it fails to format the date, the adding operation will be by String.
     *
     * @param commands User input that is split by command and description.
     * @throws EmptyDescriptionException If commands[1] is empty.
     */
    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        boolean isEmpty = commands.length == 1;
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            try {
                addByDate(commands);
            } catch (Exception e) {
                addByString(commands);
            }

        }
    }


    /**
     * Changes the date format and add into the list of task.
     *
     * @param commands User input that is split by command and description.
     */
    public void addByDate(String[] commands) {
        String commandType = commands[0];
        boolean isTodo = commandType.equals("todo");
        boolean isDeadline = commandType.equals("deadline");
        boolean isEvent = commandType.equals("event");
        
        
        int indexOfDelimiter;
        indexOfDelimiter = commands[1].indexOf("/by");

        // Index of date and time starts after delimiter
        final int INDEX_OF_DATE_TIME = indexOfDelimiter + 3;


        String dateTime = commands[1].substring(INDEX_OF_DATE_TIME);
        String newDateFormat = changeDateFormat(dateTime);
        String newCommand = commands[1].replace(dateTime, newDateFormat);

        if (isTodo) {
            TaskList.addTodo(newCommand);
        } else if (isDeadline) {
            TaskList.addDeadline(newCommand);
        } else if (isEvent) {
            TaskList.addEvent(newCommand);
        }

    }

    /**
     * Changes the date from dd-MM-yyyy format to MMM d yyyy format.
     * If time exists, change from HHmm format to hh:mm a format.
     *
     * @param description User inputted date.
     * @return Formatted date and time in string.
     */
    public String changeDateFormat(String description) {
        // Remove the leading space
        String newCommand = description.trim();

        // Split by space to separate date and time.
        String[] dateAndTime = newCommand.split(" ");

        if (dateAndTime.length == 1) {
            // Add by date only
            LocalDate date = formatDate(dateAndTime[0]);
            newCommand = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } else {
            // Add by date and time
            String date = dateAndTime[0];
            String time = dateAndTime[1];
            LocalDateTime dateTime = formatDateAndTime(date, time);
            String newDateFormat = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String newTimeFormat = dateTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            newCommand = newDateFormat + " " + newTimeFormat;
        }
        return newCommand;
    }

    /**
     * Returns the formatted date inputted by user.
     *
     * @param description User inputted date.
     * @return Formatted date.
     */
    public LocalDate formatDate(String description) {

        String[] dates = description.split("/");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        return LocalDate.of(year, month, day);

    }

    /**
     * Returns the formatted date and time that is inputted by user.
     *
     * @param date User inputted date.
     * @param time User inputted time.
     * @return Formatted date and time.
     */
    public LocalDateTime formatDateAndTime(String date, String time) {
        String[] dates = date.split("/");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        LocalDate localDate = LocalDate.of(year, month, day);



        int hour = Integer.parseInt(time.substring(0, 1));
        int min = Integer.parseInt(time.substring(2, 3));
        LocalTime localTime = LocalTime.of(hour, min);

        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Adds user inputted task into list of task based on command type.
     *
     * @param commands User input that splits into adding type and description.
     */
    public void addByString(String[] commands) {
        String commandType = commands[0];
        boolean isTodo = commandType.equals("todo");
        boolean isDeadline = commandType.equals("deadline");
        boolean isEvent = commandType.equals("event");

        String description = commands[1];
        if (isTodo) {
            TaskList.addTodo(description);
        } else if (isDeadline) {
            TaskList.addDeadline(description);
        } else if (isEvent) {
            TaskList.addEvent(description);
        }
    }

}
