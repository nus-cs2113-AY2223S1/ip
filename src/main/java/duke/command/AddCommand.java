package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class AddCommand extends Command {


    public AddCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }

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


    public void addByDate(String[] commands) {
        String commandType = commands[0];
        boolean isTodo = commandType.equals("todo");
        boolean isDeadline = commandType.equals("deadline");
        boolean isEvent = commandType.equals("event");

        int indexOfDateTime;
        if (isDeadline) {
            indexOfDateTime = commands[1].indexOf("/by");
        } else {
            indexOfDateTime = commands[1].indexOf("/at");
        }


        final int STARTINDEXOFDATETIME = indexOfDateTime + 3;

        String dateTime = commands[1].substring(STARTINDEXOFDATETIME);
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
    public String changeDateFormat(String command) {
        String newCommand = command.trim();
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

    public LocalDate formatDate(String command) {

        String[] dates = command.split("/");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        return LocalDate.of(year, month, day);

    }

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

    public void addByString(String[] commands) {
        String commandType = commands[0];
        boolean isTodo = commandType.equals("todo");
        boolean isDeadline = commandType.equals("deadline");
        boolean isEvent = commandType.equals("event");

        String command = commands[1];
        if (isTodo) {
            TaskList.addTodo(command);
        } else if (isDeadline) {
            TaskList.addDeadline(command);
        } else if (isEvent) {
            TaskList.addEvent(command);
        }
    }

}
