package duke.parser;

import duke.exceptions.*;
import duke.exceptions.NumberFormatException;
import duke.tasklist.Tasklist;
import duke.command.Command;
import duke.ui.Ui;

public class Parser {

    public Parser() {

    }

   public Command parse(String fullCommand) throws MissingArgumentException, InvalidCommandException {
       String[] ArrCommand = fullCommand.split(" ", 2);
       if (ArrCommand.length == 2) {
           String command = ArrCommand[0];
           String taskDescription = ArrCommand[1];
           return new Command(command, taskDescription);
       }
       return new Command(fullCommand, "");
   }

    public static int parseMark(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
       return task_no;
    }

    public static int parseUnmark(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
        return task_no;
    }

    public static int parseDelete(String argument) throws InvalidTaskNoException {
        int task_no = Integer.parseInt(argument) - 1;
        if (task_no >= Tasklist.tasks.size()) {
            throw new InvalidTaskNoException();
        }
        return task_no;
    }
    public static String parseDeadline(String taskDescription) throws MissingArgumentException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String description = taskDescription.split("/by ")[0];
        if (description == "") {
            throw new MissingArgumentException();
        }
        return description;
    }

    public static String parseDeadlineDate(String taskDescription) throws MissingDateException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String date = taskDescription.split("/by ")[1];
        if (date == "") {
            throw new MissingDateException();
        }
        return date;
    }

    public static String parseEvent(String taskDescription) throws MissingArgumentException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String description = taskDescription.split("/by ")[0];
        if (description == "") {
            throw new MissingArgumentException();
        }
        return description;
    }

    public static String parseEventDate(String taskDescription) throws MissingDateException, InvalidArgumentException {
        if (!taskDescription.contains("/by ")) {
            throw new InvalidArgumentException();
        }
        String date = taskDescription.split("/by ")[1];
        if (date == "") {
            throw new MissingDateException();
        }
        return date;
    }

}
